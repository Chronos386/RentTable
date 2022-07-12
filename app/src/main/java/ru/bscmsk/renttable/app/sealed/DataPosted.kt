package ru.bscmsk.renttable.app.sealed


sealed class DataPosted : Returnable() {
    object IsPosted : DataPosted()

    object NotPosted : DataPosted()
}