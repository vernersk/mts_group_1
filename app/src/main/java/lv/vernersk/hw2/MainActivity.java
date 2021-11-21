package lv.vernersk.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openDialog;
    Button goToSecond;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialog = findViewById(R.id.btnDialog);
        openDialog.setOnClickListener(v -> showGroupDialog());

        goToSecond = findViewById(R.id.btnGoToSecond);
        goToSecond.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    void showGroupDialog()
    {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.group_dialog);

        View[] checkBoxViews = new View[]{
            dialog.findViewById(R.id.cbxMember1),
            dialog.findViewById(R.id.cbxMember2),
            dialog.findViewById(R.id.cbxMember3),
            dialog.findViewById(R.id.cbxMember4)
        };

        for(View view : checkBoxViews){
            view.setOnClickListener(v -> onCheckBoxClicked(view));
        }

        Button btnOK = dialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener((v) -> setToast("You clicked OK"));

        Button btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            setToast("You closed dialog");
            dialog.dismiss();
        });

        dialog.show();
    }

    public void onCheckBoxClicked(View view){

        CheckBox checkBox = (CheckBox) view;

        if(checkBox.isChecked())
        {
            setToast(checkBox.getText() + " checked");
        }
        else
        {
            setToast(checkBox.getText() + " unchecked");
        }
    }


    public void setToast(String textMessage)
    {
        Toast.makeText(this, textMessage, Toast.LENGTH_SHORT).show();
    }
}