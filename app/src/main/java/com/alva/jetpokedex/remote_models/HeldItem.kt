package com.alva.jetpokedex.remote_models

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)