package com.gizthon.camera.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.idn.kmed.cervexa.network.CervexaApi;
import com.idn.kmed.cervexa.network.RetrofitClient;

/**
 * Login Activity for Cervexa New.
 * Consumes the backend API similar to the dev branch of cervexa-source-code.
 */
public class LoginActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Setup Login UI
        
        // Example of consuming the backend API
        // CervexaApi api = RetrofitClient.INSTANCE.getApi();
        // Call login endpoint here...
        
        Toast.makeText(this, "Backend Integrated (dev branch)", Toast.LENGTH_SHORT).show();
        
        // Proceed to Camera
        UVCUSBCameraActivity.start(this);
        finish();
    }
}
