package org.sherman.magic.weatherportfolio.Controller

import android.app.Application
import com.android.volley.VolleyLog.setTag
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue



/**
 * Created by Admin on 12/9/2017.
 */
class AppController : Application() {
    private var mRequestQueue: RequestQueue? = null

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return this!!.mRequestQueue!!
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    //    public ImageLoader getImageLoader() {
    //        getRequestQueue();
    //        if (mImageLoader == null) {
    //            mImageLoader = new ImageLoader(this.mRequestQueue,
    //                    new LruBitmapCache());
    //        }
    //        return this.mImageLoader;
    //    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.setTag(if (TextUtils.isEmpty(tag)) TAG else tag)
        requestQueue.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.setTag(TAG)
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {
        val TAG = AppController::class.java
                .simpleName
        @get:Synchronized
        var instance: AppController? = null
            private set
    }
}
