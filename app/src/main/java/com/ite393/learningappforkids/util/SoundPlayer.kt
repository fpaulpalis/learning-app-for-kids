package com.ite393.learningappforkids.util

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.*

class SoundPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    fun playLoopingSound(soundResId: Int) {
        mediaPlayer?.release() // Release previous media player instance
        mediaPlayer = MediaPlayer.create(context, soundResId).apply {
            setOnCompletionListener {
                coroutineScope.launch { delay(1000) // Small delay before repeating
                    playLoopingSound(soundResId)
                }
            }
            start()
        }
    }

    fun stopSound() {
        mediaPlayer?.release()
        mediaPlayer = null
        coroutineScope.cancel()
    }
}
