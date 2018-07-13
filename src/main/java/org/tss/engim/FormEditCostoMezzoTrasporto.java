package org.tss.engim;

import java.math.BigDecimal;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.tss.engim.db.CostoMezzoTrasporto;
import org.tss.engim.dao.DAOGenerico;


public class FormEditCostoMezzoTrasporto extends Form<CostoMezzoTrasporto>
{
  private CostoMezzoTrasporto c;

  public FormEditCostoMezzoTrasporto(String id)
  {
    super(id);
    add(new FeedbackPanel("feedback"));
    //c = new CostoMezzoTrasporto();
    
    setDefaultModel(new CompoundPropertyModel<CostoMezzoTrasporto>(c));
    
    add(new TextField("id"));
    
    //add(new TextField("nomeMezzo"));
    TextField nomeMezzo = new TextField("nomeMezzo");
    nomeMezzo.setRequired(true);
    add(nomeMezzo);
    
    //add(new TextField("pesoMassimo"));
    NumberTextField pesoMassimo = new NumberTextField("pesoMassimo");
    pesoMassimo.setStep(0.1);
    pesoMassimo.setRequired(true);
    pesoMassimo.add(new RangeValidator<>(BigDecimal.valueOf(0.1), BigDecimal.valueOf(100000)));
    add(pesoMassimo);
    
    // add(new TextField("costo"));
    NumberTextField costo = new NumberTextField("costo");
    costo.setStep(0.1);
    costo.setRequired(true);
    costo.add(new RangeValidator<>(BigDecimal.valueOf(0.1), BigDecimal.valueOf(1000000)));
  }

  @Override
  protected void onBeforeRender()
  {
    c = new CostoMezzoTrasporto();
    setDefaultModel(new CompoundPropertyModel<CostoMezzoTrasporto>(c));
    super.onBeforeRender(); //To change body of generated methods, choose Tools | Templates.
  }
  
  

  @Override
  protected void onSubmit()
  {
      DAOGenerico.inserisci_o_aggiorna(c);
      c.setId(null);
  }

  
   
}

/*
public class FormEditCostoMezzoTrasporto(String id, IModel model, IFeedback feedback) extends Form<CostoMezzoTrasporto> {
    super(id, model, feedback);
    TextField field = new TextField("required", new PropertyModel(model, "text"));
    field.setRequired(true));
    add(field);
}*/
