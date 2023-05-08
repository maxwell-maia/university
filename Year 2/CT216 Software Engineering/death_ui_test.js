//this code calls player:death_ui event
//will give the player the death ui

var DeathUiTest = pc.createScript('death_ui_test');



// initialize code called once per entity
DeathUiTest.prototype.initialize = function() {
};

// update code called every frame
DeathUiTest.prototype.update = function (dt)
{
    //check when player presses p
    if(this.app.keyboard.wasPressed(pc.KEY_L))
    {
        console.log("player:death_ui event fire");
        this.app.fire('player:death_ui');
    }
};

// swap method called for script hot-reloading
// inherit your script state here
// SpawnTest.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/