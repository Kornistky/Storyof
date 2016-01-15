package com.ltd.fix.timehelper.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ltd.fix.timehelper.Models.ModelTask;
import com.ltd.fix.timehelper.R;
import com.ltd.fix.timehelper.Utils;

import java.util.Calendar;


public class AddTaskDialogFragment extends DialogFragment {

    private AddTaskListener addTaskListener;

    public interface AddTaskListener{
        void onTaskAdded(ModelTask newTaks);
        void onTaskAddCancel();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addTaskListener = (AddTaskListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement AddTaskListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.title);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View container = inflater.inflate(R.layout.task_dialog, null);

        final TextInputLayout title = (TextInputLayout) container.findViewById(R.id.Dialog_Task_Title);
        final EditText etTitle = title.getEditText();

        final TextInputLayout date = (TextInputLayout) container.findViewById(R.id.Dialog_Task_Date);
        final EditText etDate = date.getEditText();

        TextInputLayout time = (TextInputLayout) container.findViewById(R.id.Dialog_Task_Time);
        final EditText etTime = time.getEditText();

        title.setHint(getResources().getString(R.string.task_title));
        date.setHint(getResources().getString(R.string.task_date));
        time.setHint(getResources().getString(R.string.task_time));

        builder.setView(container);

        final ModelTask task = new ModelTask();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) +1);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.length() == 0) {
                    etDate.setText(" ");
                }

                DialogFragment datePicker = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etDate.setText(null);
                    }
                };
                datePicker.show(getFragmentManager(), "DatePicker");
            }
        });


        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTime.length() == 0) {
                    etTime.setText(" ");
                }


                DialogFragment timePicker = new TimePickerFragment() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        etTime.setText(Utils.getTime(calendar.getTimeInMillis()));

                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etTime.setText(null);
                    }
                };

                timePicker.show(getFragmentManager(),"TimePicker");
            }
        });


        builder.setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                task.setTitle(etTitle.getText().toString());
                if (etDate.length() != 0 || etTime.length() != 0) {
                    task.setDate(calendar.getTimeInMillis());
                }
                addTaskListener.onTaskAdded(task);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addTaskListener.onTaskAddCancel();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButoon = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etTitle.length() == 0) {
                    positiveButoon.setEnabled(false);
                    title.setError(getResources().getString(R.string.error));
                }

                etTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (s.length() == 0) {
                            positiveButoon.setEnabled(false);
                            title.setError(getResources().getString(R.string.error));
                        } else {
                            positiveButoon.setEnabled(true);
                            title.setErrorEnabled(false);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        return alertDialog;
    }
}
