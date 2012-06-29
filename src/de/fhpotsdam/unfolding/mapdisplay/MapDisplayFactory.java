package de.fhpotsdam.unfolding.mapdisplay;


import processing.core.*;
//import processing.opengl.PGraphicsOpenGL;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

public class MapDisplayFactory {

	public static final boolean DEFAULT_USE_MASK = true;
	public static final boolean DEFAULT_USE_DISTORTION = false;

	public static final String OSM_API_KEY = "607e6483654b5c47b9056791d607ab74";
	public static final int OSM_STYLE_ID = 998;

	public static AbstractMapDisplay getMapDisplay(PApplet p, String id, float x, float y,
			float width, float height, AbstractMapProvider provider) {
		return getMapDisplay(p, id, x, y, width, height, DEFAULT_USE_MASK, DEFAULT_USE_DISTORTION,
				provider);
	}

	public static AbstractMapDisplay getMapDisplay(PApplet p, String id, float x, float y,
			float width, float height, boolean useMask, boolean useDistortion,
			AbstractMapProvider provider) {
		
		AbstractMapDisplay mapDisplay;
		
		if (provider == null) {
			provider = getDefaultProvider();
		}
		
		if (useMask) {
			
				System.out.println("Using MaskedPGraphicsMapDisplay for '" + id + "'");
				System.out.println("no rotation possible (without OpenGL)");
				mapDisplay = new MaskedPGraphicsMapDisplay(p, provider, x, y, width, height);
			
		} else {
			PApplet.println("Using ProcessingMapDisplay");
			mapDisplay = new ProcessingMapDisplay(p, provider, x, y, width, height);
		}

		return mapDisplay;
	}

	public static AbstractMapProvider getDefaultProvider() {
		return new OpenStreetMap.CloudmadeProvider(OSM_API_KEY, OSM_STYLE_ID);
	}
	
}
