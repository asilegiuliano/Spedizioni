package org.tss.engim.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.wicket.markup.html.basic.Label;
import org.tss.engim.PM;
import org.tss.engim.db.CostoMezzoTrasporto;
import org.tss.engim.db.Merce;
import org.tss.engim.db.MerceSpedizione;
import org.tss.engim.db.Spedizione;

public class SpedizioneDAO
{
    
  public static float pesoTotale(Spedizione s)
  {
    EntityManager db = PM.db();
    
    try
    
    {
      // meglio ricaricare tutto l'oggetto di nuovo invece che chiamare solo s, perchè la collection è in lazy loading e potrebbe non essere letta
      // si fa perchè l'oggetto s non è più nella memoria di hibernate quando noi arriviamo a questo punto del codice, non è in cache
      Spedizione s_reloaded = db.find(Spedizione.class, s.getId());
      // s = db.find(s.getClass(), s.getId());
      
      Collection<MerceSpedizione> merci = s_reloaded.getMerceSpedizioneCollection();
      float pesotot = 0;
      for (MerceSpedizione ms: merci)
      {
        pesotot = pesotot + ms.getQuantita() * ms.getIdMerce().getPeso().floatValue();
        // altro modo per scriverlo:
        //pesotot += ms.getIdMerce().getPeso().floatValue() * ms.getQuantita();
      }
      return pesotot;

    } 
    
    finally 
    
    {
      db.close();
    }
  }
  
  // ritorna un oggetto CostoMezzoTrasporto perchè la stringa finale è € + costo + stringa
  public static CostoMezzoTrasporto costoBasso(Spedizione s)
  {
    double pesoSpedizione = pesoTotale(s);
    
    EntityManager db = PM.db();
    
    try
    {
      
      // metodo Java per ottenere la lista di tutti i record, non tramite la namedquery
      TypedQuery<CostoMezzoTrasporto> selectAll = 
              db.createNamedQuery("CostoMezzoTrasporto.findAll", CostoMezzoTrasporto.class);
      
      
      List<CostoMezzoTrasporto> results = selectAll.getResultList();
      CostoMezzoTrasporto minimo = null;
      for (CostoMezzoTrasporto cmt: results)
      {
        if (cmt.getPesoMassimo().floatValue() >= pesoSpedizione) 
        {
          // non passa nel secondo if se non ha la condizione del primo
          if (minimo == null)
          minimo = cmt;
          else
          {
            if (cmt.getCosto().floatValue() < minimo.getCosto().floatValue())
              minimo = cmt;
          }
        }
        
      }
      
      return minimo;
      // double costo = minimo.getCosto().floatValue();
      
      // String costoString = minimo.getNomeMezzo();
      // monto la stringa, nel dao non ho riferimenti, è la pagina CostoMezzoTrasporto a occuparsi della lingua
      // return costo + " " + "(" + costoString + ")";
    }
    
    finally
    {
      db.close();
    }
  }
  
}
