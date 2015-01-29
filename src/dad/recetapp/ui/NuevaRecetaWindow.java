package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Spinner;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

import dad.recetapp.services.ServiceException;
import dad.recetapp.services.ServiceLocator;
import dad.recetapp.services.impl.CategoriasService;
import dad.recetapp.services.items.CategoriaItem;
import dad.recetapp.services.items.RecetaItem;

public class NuevaRecetaWindow extends Window implements Bindable{
	
	private RecetApp recetApp;

	@BXML private TextInput nombreText;
	@BXML private TextInput cantidadText;
	@BXML private ListButton paraListButton;
	@BXML private ListButton categoriaListButton;
	@BXML private Spinner tTotalMSpinner;
	@BXML private Spinner tTotalSSpinner;
	@BXML private Spinner tThermoMSpinner;
	@BXML private Spinner tThermoSSpinner;
	@BXML private PushButton cancelarButton;
	@BXML private PushButton crearButton;
	
	@BXML private ComponenteReceta componenteReceta;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		initCategoriaListButton();
		
		cancelarButton.getButtonPressListeners().add(new ButtonPressListener() {	
			public void buttonPressed(Button button) {
				 onCancelarButtonPressed();		
			}
		});
		
		crearButton.getButtonPressListeners().add(new ButtonPressListener() {	
			public void buttonPressed(Button button) {
				 onCrearButtonPressed();		
			}
		});
	}

	private void initCategoriaListButton() {
		java.util.List<CategoriaItem> aux;
		try {
			aux = ServiceLocator.getCategoriasService().listarCategorias();
			CategoriaItem categoria = new CategoriaItem();
			categoria.setDescripcion("<Seleccione una categor�a>");
			aux.add(0, categoria);
			categoriaListButton.setListData(convertirList(aux));
			categoriaListButton.setSelectedIndex(0);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<CategoriaItem> convertirList(java.util.List<CategoriaItem> listUtil) {
		List<CategoriaItem> aux = new ArrayList<CategoriaItem>();
		for(CategoriaItem c : listUtil) {
			aux.add(c);
		}
		return aux;
	}

//	protected void onCrearButtonPressed() {
//		RecetaItem receta = new RecetaItem();
//		CategoriaItem categoria = null;
//		try {
//			categoria = ServiceLocator.getCategoriasService().obtenerCategoria((long) 1);
//		} catch (ServiceException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		receta.setNombre(nombreText.getText());
//		receta.setCantidad(Integer.valueOf(cantidadText.getText()));
//		receta.setPara("persona");
//		receta.setTiempoTotal(tTotalMSpinner.getSelectedIndex()*60 + tTotalSSpinner.getSelectedIndex());
//		receta.setTiempoThermomix(tThermoMSpinner.getSelectedIndex()*60 + tThermoSSpinner.getSelectedIndex());
//		receta.setCategoria(categoria);
//		
//		try {
//			ServiceLocator.getRecetasService().crearReceta(receta);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	protected void onCrearButtonPressed() {
		RecetaItem receta = new RecetaItem();
		receta.setNombre(nombreText.getText());
		receta.setCantidad(Integer.valueOf(cantidadText.getText()));
		//TODO Arreglar
		receta.setPara((String) paraListButton.getSelectedItem());
		receta.setTiempoTotal(tTotalMSpinner.getSelectedIndex() * 60 + tTotalSSpinner.getSelectedIndex());
		receta.setTiempoThermomix(tThermoMSpinner.getSelectedIndex() * 60 + tThermoSSpinner.getSelectedIndex());
		receta.setCategoria((CategoriaItem)categoriaListButton.getSelectedItem());
		
		try {
			ServiceLocator.getRecetasService().crearReceta(receta);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		//TODO cuando a�ades una receta no se carga en la tabla recetas
	}

	protected void onCancelarButtonPressed() {
		close();
	}
	
	public void setRecetApp(RecetApp recetApp) {
		this.recetApp = recetApp;
		componenteReceta.setWindowsApp(recetApp);
	}
}
