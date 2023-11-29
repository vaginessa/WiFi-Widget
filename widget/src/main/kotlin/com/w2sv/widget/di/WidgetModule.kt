package com.w2sv.widget.di

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.work.WorkManager
import com.w2sv.androidutils.coroutines.getValueSynchronously
import com.w2sv.domain.model.WidgetWifiProperty
import com.w2sv.domain.repository.WidgetRepository
import com.w2sv.widget.data.appearance
import com.w2sv.widget.data.refreshing
import com.w2sv.widget.model.WidgetAppearance
import com.w2sv.widget.model.WidgetRefreshing
import com.w2sv.widget.utils.appWidgetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object WidgetModule {

    @Provides
    fun workManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    fun widgetRefreshing(widgetRepository: WidgetRepository): WidgetRefreshing =
        widgetRepository.refreshing.getValueSynchronously()

    @Provides
    fun widgetAppearance(widgetRepository: WidgetRepository): WidgetAppearance =
        widgetRepository.appearance.getValueSynchronously()

    @Provides
    fun enabledWidgetWifiProperties(widgetRepository: WidgetRepository): Set<WidgetWifiProperty> =
        widgetRepository.getEnabledWifiProperties()

    @Provides
    fun appWidgetManager(@ApplicationContext context: Context): AppWidgetManager =
        context.appWidgetManager
}
