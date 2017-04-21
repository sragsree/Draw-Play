package com.example.application.drawcircles;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DrawCircles extends AppCompatActivity {
    private View circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_circles);
        circleView = new SimpleDrawing(this);
        setContentView(circleView);
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        ((SimpleDrawing)circleView).setScreenHeight(screenSize.y);
        ((SimpleDrawing)circleView).setScreenWidth(screenSize.x);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                Log.i("Test","Red");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setColor(Color.RED);
                Toast.makeText(this, "Color : Red",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.blue:
                Log.i("Test","blue");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setColor(Color.BLUE);
                Toast.makeText(this, "Color : Blue",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.green:
                Log.i("Test","green");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setColor(Color.GREEN);
                Toast.makeText(this, "Color : Green",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.black:
                Log.i("Test","black");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setColor(Color.BLACK);
                Toast.makeText(this, "Color : Black",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.insert:
                Log.i("Test","Insert");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setMode("insert");
                Toast.makeText(this, "Mode : Insert",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Log.i("Test","Delete");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setMode("delete");
                Toast.makeText(this, "Mode : Delete",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.move:
                Log.i("Test","Move");
                item.setChecked(true);
                ((SimpleDrawing)circleView).setMode("move");
                circleView.invalidate();
                Toast.makeText(this, "Mode : Move",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.i("Test","None");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
