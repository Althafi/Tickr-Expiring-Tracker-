package com.project.tickr.domain.di

import com.project.tickr.core.util.DateTimeUtil
import com.project.tickr.core.util.DateTimeUtilImpl
import com.project.tickr.domain.error.ErrorMessageProvider
import com.project.tickr.domain.usecase.category.CreateCategoryUseCase
import com.project.tickr.domain.usecase.category.DeleteCategoryUseCase
import com.project.tickr.domain.usecase.category.GetCategoriesUseCase
import com.project.tickr.domain.usecase.category.GetCategoriesByUserUseCase
import com.project.tickr.domain.usecase.category.GetCategoryUseCase
import com.project.tickr.domain.usecase.category.UpdateCategoryUseCase
import com.project.tickr.domain.usecase.item.CreateItemUseCase
import com.project.tickr.domain.usecase.item.DeleteItemUseCase
import com.project.tickr.domain.usecase.item.GetExpiringItemsUseCase
import com.project.tickr.domain.usecase.item.GetItemUseCase
import com.project.tickr.domain.usecase.item.GetItemsByUserUseCase
import com.project.tickr.domain.usecase.item.GetItemsUseCase
import com.project.tickr.domain.usecase.item.MarkItemConsumedUseCase
import com.project.tickr.domain.usecase.item.UpdateItemUseCase
import com.project.tickr.domain.usecase.notification.DeleteNotificationUseCase
import com.project.tickr.domain.usecase.notification.GetNotificationsByItemUseCase
import com.project.tickr.domain.usecase.notification.GetNotificationsUseCase
import com.project.tickr.domain.usecase.notification.ScheduleNotificationUseCase
import com.project.tickr.domain.usecase.profile.GetProfileUseCase
import com.project.tickr.domain.usecase.profile.UpsertProfileUseCase
import org.koin.dsl.module

val domainModule = module {
    single<DateTimeUtil> { DateTimeUtilImpl() }
    single { ErrorMessageProvider() }

    factory { GetProfileUseCase(get()) }
    factory { UpsertProfileUseCase(get()) }

    factory { GetCategoriesUseCase(get()) }
    factory { GetCategoriesByUserUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
    factory { CreateCategoryUseCase(get()) }
    factory { UpdateCategoryUseCase(get()) }
    factory { DeleteCategoryUseCase(get()) }

    factory { GetItemsUseCase(get()) }
    factory { GetItemsByUserUseCase(get()) }
    factory { GetItemUseCase(get()) }
    factory { CreateItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
    factory { DeleteItemUseCase(get()) }
    factory { MarkItemConsumedUseCase(get()) }
    factory { GetExpiringItemsUseCase(get(), get()) }

    factory { GetNotificationsUseCase(get()) }
    factory { GetNotificationsByItemUseCase(get()) }
    factory { ScheduleNotificationUseCase(get(), get()) }
    factory { DeleteNotificationUseCase(get()) }
}
