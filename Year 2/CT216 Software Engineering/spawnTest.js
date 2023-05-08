var SpawnTest = pc.createScript('spawnTest');



// initialize code called once per entity
SpawnTest.prototype.initialize = function() {

};

// update code called every frame
SpawnTest.prototype.update = function (dt)
{
    //check when player presses p
    if(this.app.keyboard.wasPressed(pc.KEY_P))
    {
        this.app.fire('player:spawn');
    }
};

// swap method called for script hot-reloading
// inherit your script state here
// SpawnTest.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/