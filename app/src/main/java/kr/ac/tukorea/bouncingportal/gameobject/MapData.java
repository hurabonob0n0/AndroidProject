package kr.ac.tukorea.bouncingportal.gameobject;

import android.graphics.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapData {
    public ArrayList<Point> tileList = new ArrayList<>();
    public Point ballPosition = null;

    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();

        // 타일 위치 저장
        JSONArray tiles = new JSONArray();
        for (Point p : tileList) {
            JSONArray point = new JSONArray();
            point.put(p.x);
            point.put(p.y);
            tiles.put(point);
        }
        obj.put("tiles", tiles);

        // 볼 위치 저장
        if (ballPosition != null) {
            JSONArray ball = new JSONArray();
            ball.put(ballPosition.x);
            ball.put(ballPosition.y);
            obj.put("ball", ball);
        }

        return obj;
    }

    public static MapData fromJson(JSONObject obj) throws JSONException {
        MapData data = new MapData();

        // 타일 불러오기
        JSONArray tiles = obj.getJSONArray("tiles");
        for (int i = 0; i < tiles.length(); i++) {
            JSONArray point = tiles.getJSONArray(i);
            int x = point.getInt(0);
            int y = point.getInt(1);
            data.tileList.add(new Point(x, y));
        }

        // 볼 위치 불러오기
        if (obj.has("ball")) {
            JSONArray ball = obj.getJSONArray("ball");
            int x = ball.getInt(0);
            int y = ball.getInt(1);
            data.ballPosition = new Point(x, y);
        }

        return data;
    }
}
