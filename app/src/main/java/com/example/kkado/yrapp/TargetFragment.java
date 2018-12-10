package com.example.kkado.yrapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TargetFragment extends Fragment
{
   View myView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
   {
     myView = inflater.inflate(R.layout.fragment_target, container, false);
   return myView;
   }
}
