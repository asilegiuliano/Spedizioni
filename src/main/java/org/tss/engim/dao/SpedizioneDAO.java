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
  
  /*
  public static String costoBasso(Spedizione s)
  {
    double pesoDaTrasportare = pesoTotale(s);
    
    CostoMezzoTrasporto minimo = null;
    
    EntityManager db = PM.db();
    
    try{
      TypedQuery<CostoMezzoTrasporto> selectAll = 
              db.createNamedQuery("CostoMezzoTrasporto.findAll", CostoMezzoTrasporto.class);
      
      
      List<CostoMezzoTrasporto> tutti = selectAll.getResultList();
      for (CostoMezzoTrasporto cmt: tutti)
      {
        if (minimo == null)
          minimo = cmt;
        else
        {
          if (cmt.getPesoMassimo() >= pesoDaTrasportare &&
              cmt.getCosto() < minimo.getCosto())
            minimo = cmt;
        }
      }
      double costo = minimo.getCosto();
      
      String costoString = minimo.getNomeMezzo();
      
      return costo + " " + "(" + costoString + ")";
    }
    
    finally
    {
      db.close();
    }
  }
*/
}
