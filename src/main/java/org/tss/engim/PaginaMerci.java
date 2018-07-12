package org.tss.engim;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.tss.engim.db.Merce;

public class PaginaMerci extends PaginaBase
{
  public PaginaMerci()
  {
    List <IColumn <Merce, String>> merce = new LinkedList <>();

    PropertyColumn <Merce, String> id = new PropertyColumn<>(Model.of("Id"), "id");
    PropertyColumn <Merce, String> codice = new PropertyColumn<>(Model.of("Codice"), "codice");
    PropertyColumn <Merce, String> descrizione = new PropertyColumn<>(Model.of("Descrizione"), "descrizione");
    PropertyColumn <Merce, String> peso = new PropertyColumn<>(Model.of("Peso"), "peso");

    // COLONNA AZIONI PER AGGIORNARE, MODIFICARE O ELIMINARE
    /*
    AbstractColumn<Merce, String> azioni = new AbstractColumn<Merce, String>(Model.of("Azioni"))
    {
      @Override
      public void populateItem(Item<ICellPopulator<Merce>> item, String wicketid, IModel<Merce> imodel)
      {
        item.add(new AzioniPanel(wicketid, imodel.getObject()));
      }
    };
    */
    merce.add(id);
    merce.add(codice);
    merce.add(descrizione);
    merce.add(peso);
    // merce.add(azioni);
    
    SPDataProvider<Merce> dataprov = new SPDataProvider<>(Merce.class);
    
    DefaultDataTable table = new DefaultDataTable ("merci", merce, dataprov, 10);
    
    add(table);
    // add(new FormEditMerce("editMerci"));
  }
}
