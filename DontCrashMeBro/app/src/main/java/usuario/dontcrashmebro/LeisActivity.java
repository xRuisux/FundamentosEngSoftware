package usuario.dontcrashmebro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class LeisActivity extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leis);

        pdfView = (PDFView) findViewById(R.id.pdfView);

        pdfView.fromAsset("Leis.pdf").load();
    }
}
