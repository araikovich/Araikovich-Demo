package araikovich.inc.araikovichdemo.ui.base.livedata

sealed class ActionState {
    object LOADING : ActionState()
    object SUCCESS : ActionState()
    object ERROR : ActionState()
}