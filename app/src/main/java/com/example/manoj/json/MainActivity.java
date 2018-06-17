package com.example.manoj.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.json.Model.ItuneStuff;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtType;
    TextView txtartistname;
    TextView txtcollectionname;
    TextView txttrackname;
    TextView txtkind;
    ImageView imgart;
    String imgurl;
    Button btnjson;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtType=(TextView)findViewById(R.id.txtType);
        txtartistname=(TextView)findViewById(R.id.txtartistname);
        txtcollectionname=(TextView)findViewById(R.id.txtcollectionname);
        txtkind=(TextView)findViewById(R.id.txtkind);
        txttrackname=(TextView)findViewById(R.id.txttrackname);
        imgart=(ImageView)findViewById(R.id.imgart);
        btnjson=(Button)findViewById(R.id.btnjson);
        btnjson.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        JSONItunesStuffTask jsonItunesStuffTask=new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();
    }

    private class JSONItunesStuffTask extends AsyncTask<String,Void,ItuneStuff>
    {
        Context context;
        ProgressDialog progressDialog;
        public JSONItunesStuffTask(Context context)
        {
            this.context=context;
            progressDialog=new ProgressDialog(context);
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog.setTitle("Downloading Info from Itunes.....Please wait");
            progressDialog.show();
        }
        @Override
        protected ItuneStuff doInBackground(String... params)
        {
            ItuneStuff ituneStuff=new ItuneStuff();
            itunesHttpClient ItunesHttpClient=new itunesHttpClient();
            String data=(ItunesHttpClient.getItunesStuffData());

            try {
                ituneStuff=JsonItuneParse.getItunesStuff(data);
                imgurl=ituneStuff.getArtistviewurl();
                bitmap=(ItunesHttpClient.getBitmapFromUrl(imgurl));
            }catch (Throwable t)
            {
                t.printStackTrace();
            }
            return ituneStuff;
        }
        @Override
        protected void onPostExecute(ItuneStuff ituneStuff)
        {
            super.onPostExecute(ituneStuff);

        txtartistname.setText(ituneStuff.getArtistname());
        txtcollectionname.setText(ituneStuff.getCollectionname());
        txtkind.setText(ituneStuff.getKind());
        txttrackname.setText(ituneStuff.getTrackname());
        txtType.setText(ituneStuff.getType());
        imgart.setImageBitmap(bitmap);
        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }

        }
    }
}
