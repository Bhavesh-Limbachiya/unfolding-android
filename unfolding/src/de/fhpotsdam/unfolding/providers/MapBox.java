package de.fhpotsdam.unfolding.providers;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.OpenStreetMap.OpenStreetMapProvider;

public class MapBox {

	public static abstract class MapBoxProvider extends OpenStreetMapProvider {

		public MapBoxProvider() {
			super();
		}

		public String getZoomString(Coordinate coordinate) {
			// Rows are numbered from bottom to top (opposite to OSM)
			float gridSize = PApplet.pow(2, coordinate.zoom);
			float negativeRow = gridSize - coordinate.row - 1;

			return (int) coordinate.zoom + "/" + (int) coordinate.column + "/" + (int) negativeRow;
		}
	}

	public static class WorldLightProvider extends MapBoxProvider {
		public String[] getTileUrls(Coordinate coordinate) {
			String url = "http://c.tile.mapbox.com/1.0.0/world-light/" + getZoomString(coordinate)
					+ ".png";
			return new String[] { url };
		}
	}

}
