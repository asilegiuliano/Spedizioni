package org.tss.engim;

import java.util.Date;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.DateValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.tss.engim.dao.DAOGenerico;
import org.tss.engim.db.Spedizione;

public class FormEditSpedizioni extends Form<Spedizione>
{
  private Spedizione s;
  public FormEditSpedizioni(String id)
  {
    super(id);
    add(new FeedbackPanel("feedback"));
    s = new Spedizione();
    
    setDefaultModel(new CompoundPropertyModel<Spedizione>(s));
    
    // add(new TextField("id"));
    
    // add(new TextField("numero"));
    NumberTextField numero = new NumberTextField("numero");
    numero.setStep(0.1);
    numero.setRequired(true);
    numero.add(RangeValidator.range(0, 10000));
    add(numero);
        
    //add(new TextField("data"));
    TextField datatxt = new TextField("data");
    datatxt.add(DateValidator.maximum(new Date()));
    datatxt.setRequired(true);
    add(datatxt);
  }

  @Override
  protected void onBeforeRender()
  {
    s = new Spedizione();
    setDefaultModel(new CompoundPropertyModel<Spedizione>(s));
    super.onBeforeRender(); 
  }
  
  

  @Override
  protected void onSubmit()
  {
      DAOGenerico.inserisci_o_aggiorna(getModelObject());
      s.setId(null);
  }
  
}
