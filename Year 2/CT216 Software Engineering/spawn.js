//This script is for player spawning
//This script teleports the player to one of a defined set of spawn locations

var Spawn = pc.createScript('spawn');

//the player
// Spawn.attributes.add('player', {
//     type: 'entity'
// });

// //amount of spawn locations
// Spawn.attributes.add('numSpawnLocations', {
//     type: 'number',
//     default: 0
// });

// //array of spawn locations for x co-ordinate
// Spawn.attributes.add('xSpawn', {
//     type: 'number',
//     array: true,
//     default: [0]
// });

// //array of spawn locations for y co-ordinate
// Spawn.attributes.add('ySpawn', {
//     type: 'number',
//     array: true,
//     default: [0]
// });

// //array of spawn locations for z co-ordinate
// Spawn.attributes.add('zSpawn', {
//     type: 'number',
//     array: true,
//     default: [0]
// });



// initialize code called once per entity
Spawn.prototype.initialize = function () {

    //the player
    var player = this.entity.findByName('Player');

    //When adding a new spawn location, add the x, y and z co-ordinates to their respective arrays.
    //array of spawn locations for x co-ordinate
    var xSpawn = [0, 0, 0];

    //array of spawn locations for y co-ordinate
    var ySpawn = [0, 0, 0];

    //array of spawn locations for z co-ordinate
    var zSpawn = [1.288, 21, 69];

    var numSpawnLocations = xSpawn.length;



    //Method to call to spawn the player (done by firing player:spawn)
    var onSpawn = function() {
        console.log("time to spawn");
        console.log(numSpawnLocations);
        //var player = this.entity.findByName('Player');
        

        //pick a spawn location at random
        //get random int between 0 and (numSpawnLocations - 1)
        
        var i = Math.floor(Math.random() * numSpawnLocations);

        //teleport player to new position
        //this.player2.setPosition(new pc.Vec3(this.xSpawn[i], this.ySpawn[i], this.zSpawn[i]), pc.Vec3.ZERO);
        //player.entity.rigidbody.teleport(new pc.Vec3(this.xSpawn[i], this.ySpawn[i], this.zSpawn[i]), pc.Vec3.ZERO);
        //player.rigidbody.teleport(new pc.Vec3(0, 0, 21), pc.Vec3.ZERO);
        player.rigidbody.teleport(new pc.Vec3(xSpawn[i], ySpawn[i], zSpawn[i]), pc.Vec3.ZERO);
        //player.setPosition(new pc.Vec3(this.xSpawn[i], this.ySpawn[i], this.zSpawn[i]), pc.Vec3.ZERO);

        //var killfeedgroup = this.entity.findByName('killfeedgroup');
        console.log(this.numSpawnLocations);
        this.numSpawnLocations = 12;
        console.log(this.numSpawnLocations);
        
    };



    // listen for the player:spawn event
    this.app.on('player:spawn', onSpawn);

    // remove player:spawn event listeners when script destroyed
    this.on('destroy', function() {
        this.app.off('player:spawn', onSpawn);
    });
};

// Spawn.prototype.onSpawn = function() {
// //     console.log("time to spawn");

// //     //var player = this.app.root.findByName('Player');

// //     //pick a spawn location at random
// //     //get random int between 0 and (numSpawnLocations - 1)
// //     var i = 1;

// //     //teleport player to new position
// //     this.player2.entity.rigidbody.teleport(new pc.Vec3(this.xSpawn[i], this.ySpawn[i], this.zSpawn[i]), pc.Vec3.ZERO);
// };

// update code called every frame
Spawn.prototype.update = function(dt) {

};

// swap method called for script hot-reloading
// inherit your script state here
// Spawn.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/