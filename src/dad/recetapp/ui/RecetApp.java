package dad.recetapp.ui;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import dad.recetapp.ui.InicialWindow;
import dad.recetapp.ui.PrincipalWindow;

public class RecetApp implements Application{
	private Display primaryDisplay = null;
	
	private InicialWindow inicialWindow = null;	
	private PrincipalWindow principalWindow = null;
	private NuevaRecetaWindow nuevaRecetaWindow = null;
	private EditarRecetaWindow editarRecetaWindow = null;
	private NuevaInstruccionDialog nuevaInstruccionWindow = null;
	private EditarInstruccionDialog editarInstruccionWindow = null;
	
	public Window loadWindow(String bxmlFile) throws IOException, SerializationException {
		URL bxmlUrl = RecetApp.class.getResource(bxmlFile);
		BXMLSerializer serializer = new BXMLSerializer();
		return (Window) serializer.readObject(bxmlUrl);
	}
	
	public Component loadComponent(String bxmlFile) throws IOException, SerializationException {
		URL bxmlUrl = RecetApp.class.getResource(bxmlFile);
		BXMLSerializer serializer = new BXMLSerializer();
		return (Component) serializer.readObject(bxmlUrl);
	}
	
	@Override
	public void startup(Display display, Map<String, String> properties)
			throws Exception {
		primaryDisplay = display;
		openInicialWindow();
	}
	
	public void openInicialWindow() {
		try {
			inicialWindow = (InicialWindow) loadWindow("/dad/recetapp/ui/bxml/InicialWindow.bxml");
			inicialWindow.setRecetApp(this);
			inicialWindow.open(primaryDisplay);
		} catch (IOException | SerializationException e) {
			e.printStackTrace();
		}
	}
	
	public void openPrincipalWindow() {
		try {
			principalWindow = (PrincipalWindow) loadWindow("/dad/recetapp/ui/bxml/PrincipalWindow.bxml");
			principalWindow.setRecetApp(this);
			principalWindow.open(primaryDisplay);
		} catch (IOException | SerializationException e) {
			e.printStackTrace();
		}
	}
	
	public void openNuevaRecetaWindow() {
		try {
			nuevaRecetaWindow = (NuevaRecetaWindow) loadWindow("/dad/recetapp/ui/bxml/NuevaRecetaWindow.bxml");
			nuevaRecetaWindow.setRecetApp(this);
			nuevaRecetaWindow.open(primaryDisplay);
		} catch (IOException | SerializationException e) {
			e.printStackTrace();
		}
	}
	
	
	public void openEditarRecetaWindow() {
		try {
			editarRecetaWindow = (EditarRecetaWindow) loadWindow("/dad/recetapp/ui/bxml/EditarRecetaWindow.bxml");
			editarRecetaWindow.setRecetApp(this);
			editarRecetaWindow.open(primaryDisplay);
		} catch (IOException | SerializationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean shutdown(boolean optional) throws Exception {
		if(inicialWindow !=null)
		inicialWindow.close();
		if(principalWindow !=null)
		principalWindow.close();
		if(nuevaRecetaWindow !=null)
		nuevaRecetaWindow.close();
		if(editarRecetaWindow !=null)
		editarRecetaWindow.close();
		if(nuevaInstruccionWindow !=null)
		nuevaInstruccionWindow.close();
		if(editarInstruccionWindow !=null)
		editarInstruccionWindow.close();
		return false;
	}
	
	@Override
	public void suspend() throws Exception { }

	@Override
	public void resume() throws Exception {	}

	public InicialWindow getInicialWindow() {
		return inicialWindow;
	}

	public PrincipalWindow getPrincipalWindow() {
		return principalWindow;
	}
	
	public NuevaRecetaWindow getNuevaRecetaWindow() {
		return nuevaRecetaWindow;
	}
	
	public EditarRecetaWindow getEditarRecetaWindow() {
		return editarRecetaWindow;
	}
}
