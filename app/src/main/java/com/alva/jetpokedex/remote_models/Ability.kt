package com.alva.jetpokedex.remote_models

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)