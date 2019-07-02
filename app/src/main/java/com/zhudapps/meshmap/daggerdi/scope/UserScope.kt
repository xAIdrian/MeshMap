package com.zhudapps.meshmap.daggerdi.scope

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by adrian mohnacs on 2019-06-28
// */
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class UserScope {
}