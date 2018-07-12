package org.tss.engim;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.tss.engim.db.CostoMezzoTrasporto;

public class PaginaCosti extends PaginaBase
{
  public PaginaCosti()
  {
    List <IColumn <CostoMezzoTrasporto, String>> costi = new LinkedList <>();

    PropertyColumn <CostoMezzoTrasporto, String> id = new PropertyColumn<>(Model.of("Id"), "id");
    PropertyColumn <CostoMezzoTrasporto, String> nome_mezzo = new PropertyColumn<>(Model.of("Nome Mezzo"), "nomeMezzo");
    PropertyColumn <CostoMezzoTrasporto, String> peso_massimo = new PropertyColumn<>(Model.of("Peso Massimo"), "pesoMassimo");
    PropertyColumn <CostoMezzoTrasporto, String> costo = new PropertyColumn<>(Model.of("Costo"), "costo");

    // COLONNA AZIONI PER AGGIORNARE, MODIFICARE O ELIMINARE
    /*
    AbstractColumn<CostoMezzoTrasporto, String> azioni = new AbstractColumn<CostoMezzoTrasporto, String>(Model.of("Azioni"))
    {
      @Override
      public void populateItem(Item<ICellPopulator<CostoMezzoTrasporto>> item, String wicketid, IModel<CostoMezzoTrasporto> imodel)
      {
        item.add(new AzioniPanel(wicketid, imodel.getObject()));
      }
    };
    */
    costi.add(id);
    costi.add(nome_mezzo);
    costi.add(peso_massimo);
    costi.add(costo);
    // costi.add(azioni);
    
    SPDataProvider<CostoMezzoTrasporto> dataprov = new SPDataProvider<>(CostoMezzoTrasporto.class);
    
    DefaultDataTable table = new DefaultDataTable ("costi", costi, dataprov, 10);
    
    add(table);
    // add(new FormEditCostoMezzoTrasporto("editCosto"));
  }
}
