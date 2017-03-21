package akarsh.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json_result;
    ParametersAdapter parametersAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listView=(ListView)findViewById(R.id.list_item);
        parametersAdapter=new ParametersAdapter(this,R.layout.row_layout);

        listView.setAdapter(parametersAdapter);
        json_result=getIntent().getExtras().getString("Json data");//its the key which we have mentioned in main activity in putExtras()
        try {
            jsonObject=new JSONObject(json_result);
            jsonArray=jsonObject.getJSONArray("Sheet1");//sheet1 is the json array name which was created when google spreadsheet was created
            int count=0;
            String param1="",param2;
            while(count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);//like every row in json array
                param1=jo.getString("name");
                param2=jo.getString("country");
                Parameters parameters=new Parameters(param1,param2);
                parametersAdapter.add(parameters);//adding parameters to adapter
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
