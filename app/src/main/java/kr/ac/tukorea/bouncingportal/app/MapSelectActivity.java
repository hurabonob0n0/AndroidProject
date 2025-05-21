package kr.ac.tukorea.bouncingportal.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;


import kr.ac.tukorea.bouncingportal.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;

public class MapSelectActivity extends AppCompatActivity {
    private ListView listView;
    private File[] mapFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_select);

        listView = findViewById(R.id.map_list);

        File dir = getExternalFilesDir("maps");
        if (dir == null || !dir.exists()) {
            dir.mkdirs(); // 폴더가 없다면 생성
        }

        mapFiles = dir.listFiles((d, name) -> name.endsWith(".json"));
        ArrayList<String> mapNames = new ArrayList<>();
        if (mapFiles != null) {
            for (File file : mapFiles) {
                mapNames.add(file.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mapNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            File selectedFile = mapFiles[position];
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("map_path", selectedFile.getAbsolutePath());
            startActivity(intent);
        });
    }
}