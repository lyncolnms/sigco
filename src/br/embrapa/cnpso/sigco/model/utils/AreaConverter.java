//package br.embrapa.cnpso.sigco.model.utils;
//
//import javax.ejb.Stateful;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import br.embrapa.cnpso.sigco.model.Area;
//
//@Named
//@Stateful
//@FacesConverter(forClass = Area.class)
//public class AreaConverter implements Converter {
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component,
////			Object object) {
//		Area area = (Area) object;
//		if (area == null || area.getDescricao() == null)
//			return null;
//		return String.valueOf(area.getDescricao());
//	}
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component,
//			String string) {
//		if (string == null || string.isEmpty())
//			return null;
//		Area area = em.find(Area.class, string);
//		return area;
//	}
//
//}