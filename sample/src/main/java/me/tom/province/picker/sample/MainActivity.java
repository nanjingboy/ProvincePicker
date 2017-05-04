package me.tom.province.picker.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.tom.province.picker.ProvincePicker;
import me.tom.province.picker.model.Province;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Province mSelectedProvince;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProvincePicker provincePicker = new ProvincePicker(MainActivity.this);
                provincePicker.show(mSelectedProvince, new ProvincePicker.IItemClickListener() {
                    @Override
                    public void onItemClick(Province province) {
                        mSelectedProvince = province;
                        mTextView.setText(province.name);
                    }
                });
            }
        });
    }
}
