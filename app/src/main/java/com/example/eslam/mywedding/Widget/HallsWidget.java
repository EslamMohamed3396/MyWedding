package com.example.eslam.mywedding.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.R;

import java.util.List;

public class HallsWidget extends AppWidgetProvider {
    private static List<String> strings;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        strings = Shared_Preferences.getList(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.halls_widget);
        StringBuilder stringsBuilder = new StringBuilder();
        for (String ingredients : strings) {
            stringsBuilder.append(" ").append(ingredients).append("\n");
        }
        views.setTextViewText(R.id.appwidget_text, stringsBuilder);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

