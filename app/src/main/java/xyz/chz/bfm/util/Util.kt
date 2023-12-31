package xyz.chz.bfm.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import xyz.chz.bfm.util.command.SettingCmd
import xyz.chz.bfm.util.command.TermCmd

object Util {
    var isProxyed = TermCmd.isProxying()
    private val handler = Handler(Looper.getMainLooper())

    fun runOnUiThread(action: () -> Unit) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(action)
        } else {
            action.invoke()
        }
    }

    val isClashOrSing: Boolean
        get() {
            if (SettingCmd.core.contains("clash") or SettingCmd.core.contains("sing-box"))
                return true
            return false
        }

    fun copyToClipboard(context: Context, str: String?) {
        (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("copied", str))
    }
}