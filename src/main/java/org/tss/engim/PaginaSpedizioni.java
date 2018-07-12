package org.tss.engim;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.tss.engim.dao.SpedizioneDAO;
import org.tss.engim.db.CostoMezzoTrasporto;
// import org.engim.db.CostoMezzoTrasporto;
import org.tss.engim.db.Spedizione;

public class PaginaSpedizioni extends PaginaBase
{
  public PaginaSpedizioni()
  {
    List <IColumn <Spedizione, String>> spedizione = new LinkedList <>();

    PropertyColumn <Spedizione, String> id = new PropertyColumn<>(Model.of("Id"), "id");
    PropertyColumn <Spedizione, String> numero = new PropertyColumn<>(Model.of("Numero Spedizione"), "numero");
    PropertyColumn <Spedizione, String> data = new PropertyColumn<>(Model.of("Data Spedizione"), "data");
    
    // ABSTRACT COLUMN PESO TOTALE
    
    AbstractColumn <Spedizione, String> pesoTot = new AbstractColumn <Spedizione, String>(Model.of("Peso Totale"))
    {
      @Override
      public void populateItem(Item<ICellPopulator<Spedizione>> item, String wicketid, IModel<Spedizione> rowmodel)
      {
        String pesoTot = String.valueOf(SpedizioneDAO.pesoTotale(rowmodel.getObject()));
        Label cf = new Label (wicketid, "" + pesoTot);
        item.add(cf);
      }
    };
    
    // ABSTRACT COLUMN COSTO TOTALE
    
    AbstractColumn <Spedizione, String> mdt = new AbstractColumn <Spedizione, String>(Model.of("Mezzo"))
    {
      @Override
      public void populateItem(Item<ICellPopulator<Spedizione>> item, String wicketid, IModel<Spedizione> rowmodel)
      {
        CostoMezzoTrasporto cmt = SpedizioneDAO.costoBasso(rowmodel.getObject());
        String mezzo = "€" + cmt.getCosto() + " (" + cmt.getNomeMezzo() + ")";
        Label cf = new Label (wicketid, "" + mezzo);
        item.add(cf);
      }
    };
    
    // COLONNA AZIONI PER AGGIORNARE, MODIFICARE O ELIMINARE
    
    AbstractColumn<Spedizione, String> azioni = new AbstractColumn<Spedizione, String>(Model.of("Azioni"))
    {
      @Override
      public void populateItem(Item<ICellPopulator<Spedizione>> item, String wicketid, IModel<Spedizione> imodel)
      {
        item.add(new AzioniPanel(wicketid, imodel.getObject()));
      }
    };
    
    spedizione.add(id);
    spedizione.add(numero);
    spedizione.add(data);
    spedizione.add(pesoTot);
    spedizione.add(mdt);
    spedizione.add(azioni);
        
    SPDataProvider<Spedizione> dataprov = new SPDataProvider<>(Spedizione.class);
    
    DefaultDataTable table = new DefaultDataTable ("spedizioni", spedizione, dataprov, 10);
    
    add(table);
    
    // add(new FormEditSpedizioni("editSpedizioni"));
  }

}
