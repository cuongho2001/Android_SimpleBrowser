package edu.temple.agarbagebrowser;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText urlText;
    Button goButton;
    String address;
    ViewPager viewPager;
    SwipeAdapter swipeAdapter;
    List<WebViewFragment> fragmentList;
    String updatedURL;
    WebView updatedWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.my_toolbar));

        fragmentList = new ArrayList<WebViewFragment>();
        fragmentList.add(new WebViewFragment());

        urlText =  findViewById(R.id.urlText);
        goButton =  findViewById(R.id.goButton);
        viewPager =  findViewById(R.id.webPager);

        swipeAdapter = new SwipeAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(swipeAdapter);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                            if (!urlText.getText().toString().startsWith("https://"))
                                address = "https://" + urlText.getText().toString();
                            else
                                address = urlText.getText().toString();

                            urlText.setText(address);

                            WebViewFragment workingFragment =  swipeAdapter.getItem(viewPager.getCurrentItem());
                            WebView webView = workingFragment.webView;
                            webView.getSettings().setJavaScriptEnabled(true);
                            webView.getSettings().setLoadsImagesAutomatically(true);
                            webView.setWebViewClient(new WebViewClient());
                            workingFragment.urlAddress = address;
                            webView.loadUrl(address);



            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.previous_tab:
                if (viewPager.getCurrentItem()>=0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    updatedURL = swipeAdapter.getItem(viewPager.getCurrentItem()).urlAddress;
                    urlText.setText(updatedURL);
                    updatedWebView = swipeAdapter.getItem(viewPager.getCurrentItem()).webView;
                    if (updatedWebView != null) {
                        updatedWebView.getSettings().setJavaScriptEnabled(true);
                        updatedWebView.getSettings().setLoadsImagesAutomatically(true);
                        updatedWebView.setWebViewClient(new WebViewClient());
                        updatedWebView.loadUrl(updatedURL);
                    }
                }

                return true;

            case R.id.new_tab:
                fragmentList.add(new WebViewFragment());
                swipeAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(swipeAdapter.getCount()-1);
                urlText.setText("");

                return true;

            case R.id.next_tab:
                if (viewPager.getCurrentItem()<swipeAdapter.getCount()-1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    updatedURL = swipeAdapter.getItem(viewPager.getCurrentItem()).urlAddress;
                    urlText.setText(updatedURL);
                    updatedWebView = swipeAdapter.getItem(viewPager.getCurrentItem()).webView;
                    if (updatedWebView != null) {
                        updatedWebView.getSettings().setJavaScriptEnabled(true);
                        updatedWebView.getSettings().setLoadsImagesAutomatically(true);
                        updatedWebView.setWebViewClient(new WebViewClient());
                        updatedWebView.loadUrl(updatedURL);
                    }
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
