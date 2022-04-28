import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        boolean query_success = true;
        double lrlon = params.get("lrlon");
        double ullon = params.get("ullon");
        double lrlat = params.get("lrlat");
        double ullat = params.get("ullat");
        double w = params.get("w");
        double h = params.get("h");
        double desiredLonDPP = (lrlon - ullon) / w;    //LonDPP of the query box. Units of longitude per pixel

        // LonDPP for each level, a descending sorted list
        double [] lonDPP = new double[8];
        double d1 = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / 2 / MapServer.TILE_SIZE;
        lonDPP[0] = d1;
        for (int i = 1; i < 8; i++) {
            lonDPP[i] = lonDPP[i-1]/2;
        }
//        System.out.println(Arrays.toString(lonDPP));
//        System.out.println(desiredLonDPP);

        // Calculate the level of zoom we need
        int depth = 0;
        if (desiredLonDPP <= lonDPP[6]){
            depth = 7;
        } else if (desiredLonDPP <= lonDPP[5]){
            depth = 7;
        } else if (desiredLonDPP <= lonDPP[4]){
            depth = 6;
        } else if (desiredLonDPP <= lonDPP[3]){
            depth = 5;
        } else if (desiredLonDPP <= lonDPP[2]){
            depth = 4;
        } else if (desiredLonDPP <= lonDPP[1]){
            depth = 3;
        } else if (desiredLonDPP <= lonDPP[0]){
            depth = 2;
        } else {
            depth = 1;
        }
        System.out.println(depth);
        /** raster_ul_lon < ullon and raster_ul_lon is the largest possible
         *  raster_ul_lat > ullat
         *  raster_lr_lon > lrlon
         *  raster_lr_lat < lrlat
         */
        double lon = MapServer.ROOT_LRLON - MapServer.ROOT_ULLON;
        double lat = MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT;
        double raster_ul_lon = ullon - MapServer.ROOT_ULLON;
        double raster_ul_lat = MapServer.ROOT_ULLAT - ullat;
        double raster_lr_lon = lrlon - MapServer.ROOT_ULLON;
        double raster_lr_lat = MapServer.ROOT_ULLAT - lrlat;

        double interval_lon = lon / Math.pow(2, depth);
        double interval_lat = lat / Math.pow(2, depth);
        int upper_x = raster_ul_lon < 0 ? 0 : (int) (raster_ul_lon / interval_lon);
        int upper_y = raster_ul_lat < 0 ? 0 : (int) (raster_ul_lat / interval_lat);
        int lower_x = lrlon > MapServer.ROOT_LRLON ?  (int)Math.pow(2, depth) - 1 : (int) (raster_lr_lon / interval_lon);
        int lower_y = lrlat < MapServer.ROOT_LRLAT ? (int)Math.pow(2, depth) - 1 : (int) (raster_lr_lat / interval_lat);

        System.out.println(upper_x);
        System.out.println(upper_y);
        System.out.println(lower_x);
        System.out.println(lower_y);


        raster_ul_lon = MapServer.ROOT_ULLON + upper_x * interval_lon;
        raster_ul_lat = MapServer.ROOT_ULLAT - upper_y * interval_lat;
        raster_lr_lon = MapServer.ROOT_ULLON + lower_x * interval_lon;
        raster_lr_lat = MapServer.ROOT_ULLAT - lower_y * interval_lat;

        int [][] render_grid_value_x = new int[lower_y-upper_y+1][lower_x-upper_x+1];
        for (int row = 0; row < lower_y-upper_y+1; row++) {
            for (int col = 0; col < lower_x-upper_x+1; col++) {
                if (row == 0 && col == 0) {
                    render_grid_value_x[row][col] = upper_x;
                } else if (row == 0) {
                    render_grid_value_x[row][col] = render_grid_value_x[0][col-1] + 1;
                } else {
                    render_grid_value_x[row][col] = render_grid_value_x[row-1][col];
                }
            }
        }
        int [][] render_grid_value_y = new int[lower_y-upper_y+1][lower_x-upper_x+1];
        for (int row = 0; row < lower_y-upper_y+1; row++) {
            for (int col = 0; col < lower_x-upper_x+1; col++) {
                if (row == 0) {
                    render_grid_value_y[row][col] = upper_y;
                } else {
                    render_grid_value_y[row][col] = render_grid_value_y[row-1][col] + 1;
                }
            }
        }
        String[][] render_grid = new String[lower_y-upper_y+1][lower_x-upper_x+1];
        for (int row = 0; row < lower_y-upper_y+1; row++) {
            for (int col = 0; col < lower_x-upper_x+1; col++) {
                render_grid[row][col] = String.format("d%d_x%d_y%d.png", depth, render_grid_value_x[row][col], render_grid_value_y[row][col]);
            }
        }
        System.out.println(Arrays.deepToString(render_grid));

        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("depth", depth);
        results.put("query_success", query_success);

        return results;
    }
}
