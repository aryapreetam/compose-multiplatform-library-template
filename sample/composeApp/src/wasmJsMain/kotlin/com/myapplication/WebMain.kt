package com.myapplication

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
// Entry point for the Web (WasmJs) target, sets up the Compose viewport in the browser's document body
fun main() {
  ComposeViewport(document.body!!) {
    App()
  }
}
