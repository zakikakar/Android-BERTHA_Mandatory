package zakiexample.myfirstapp.bertha_mandatory;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    @Override
    protected CharSequence doInBackground(String... urls) {
        String urlString = urls[0];
        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result.toString();
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e("ERROR", errorMessage);
            return errorMessage;
        }
    }
}
