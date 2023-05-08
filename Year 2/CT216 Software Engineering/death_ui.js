//death_ui is the bit that says. respawning in a few seconds

//Careful!!!! This script is death_ui.js but it is referenced as deathUi
var DeathUi = pc.createScript('deathUi'); //declared as deathUi here

//timer is a number
DeathUi.attributes.add('timer', {
    type: 'number'
});

//1 = timer active. 0 = not counting timer
DeathUi.attributes.add('timerActive', {
    type: 'number'
});

//1 = respawn ready. 0 = respawn not ready
DeathUi.attributes.add('respawnReady', {
    type: 'number'
});

//1 = player wants respawn. 0 = player doesn't yet want respawn
DeathUi.attributes.add('playerWantsRespawn', {
    type: 'number'
});

//death ui elements
DeathUi.attributes.add('screen_tint', {
    type: 'entity'
});

DeathUi.attributes.add('respawn_timer', {
    type: 'entity'
});

DeathUi.attributes.add('respawn_available_in_text', {
    type: 'entity'
});

// initialize code called once per entity
DeathUi.prototype.initialize = function()
{
    // Disabling the context menu stops the browser displaying a menu when
    // you right-click the page
    this.app.mouse.disableContextMenu();

    // //listener for mouse press
    // this.app.mouse.on(pc.EVENT_MOUSEDOWN, this.onMouseDown, this);

    //get death_ui entity to access it's children later
    var deathui = this.entity.findByName('deathui');

    //death_ui elements are disables initially
    this.screen_tint.element.enabled = false;
    this.respawn_timer.element.enabled = false;
    this.respawn_available_in_text.element.enabled = false;

    //function that runs on death_ui event
    //bring up the death _ui
    var onDeath_ui = function() {
        console.log("death_ui should appear");


        //wait 1 seconwd. this.ischeck= true; is the lines of code that 
        //     setTimeout(() => {
        //     this.ischeck= true;
        // }, 1000);

        //enable ui elements
        deathui.script.deathUi.screen_tint.element.enabled = true;
        deathui.script.deathUi.respawn_timer.element.enabled = true;
        deathui.script.deathUi.respawn_available_in_text.element.enabled = true;

        //start the timer by setting active. (timer ticks in update)
        deathui.script.deathUi.timerActive = 1;
        //set the timer. count downfrom 5 to 0
        deathui.script.deathUi.timer = 5;


    };


    // listen for the player:spawn event
    this.app.on('player:death_ui', onDeath_ui);

    // remove player:spawn event listeners when script destroyed
    this.on('destroy', function() {
        this.app.off('player:death_ui', onDeath_ui);
    });
};

// update code called every frame
DeathUi.prototype.update = function(dt)
{
    //countdown 5 seconds when dead

    //if the timer is active
    if(this.timerActive == 1)
    {
        //update the timer with the time that has passed since the last frame
        this.timer -= dt;
        var countdownNumber = 5;

        console.log("this.timer: "+this.timer);
        
        //count down
        if(this.timer <= 4)
        {
            countdownNumber = 4;
            if(this.timer <= 3)
            {
                countdownNumber = 3;
                if(this.timer <= 2)
                {
                    countdownNumber = 2;
                    if(this.timer <= 1)
                    {
                        countdownNumber = 1;
                        if(this.timer <= 0)
                        {
                            this.respawn_timer.element.enabled = false;


                            //respawn ready
                            this.respawn_available_in_text.element.color = new pc.Color(0.34118,0.89412,0.10588);
                            this.respawn_available_in_text.setLocalPosition(5.00, -147.374, 0);
                            this.respawn_available_in_text.element.text = "Click to respawn";
                            this.respawnReady = 1;
                        }
                    }
                }
            }
        }

        this.respawn_timer.element.text = ""+countdownNumber+"";

        //Player wants respawn ?
        if(this.app.mouse.wasReleased((pc.MOUSEBUTTON_LEFT)))
        {
            //Allow the player to switch between respawning and not respawning
            if(this.playerWantsRespawn == 0)
            {
                this.playerWantsRespawn = 1;
                this.respawn_available_in_text.element.text = "Respawning in ";
                this.respawn_available_in_text.element.color = new pc.Color(0.34118,0.89412,0.10588);
                this.respawn_timer.element.color = new pc.Color(0.34118,0.89412,0.10588);
                this.respawn_timer.setLocalPosition(86, -147.374, 0);
                //118.901
            }
            else if (this.playerWantsRespawn == 1)
            {
                this.playerWantsRespawn = 0;
                this.respawn_available_in_text.element.text = "Respawn available in ...";
                this.respawn_available_in_text.element.color = new pc.Color(1,1,1);
                this.respawn_timer.element.color = new pc.Color(1,1,1);
                this.respawn_timer.setLocalPosition(149.836, -147.374, 0);
                //149.836
            }
        }

        if(this.respawnReady == 1 && this.playerWantsRespawn == 1)
        {
            //Respawn
            console.log("Respawn");

            //disable all death ui elements
            this.screen_tint.element.enabled = false;
            this.respawn_timer.element.enabled = false;
            this.respawn_available_in_text.element.enabled = false;

            //reset ui elements
            this.respawn_available_in_text.element.text = "Respawn available in ...";
            this.respawn_available_in_text.element.color = new pc.Color(1,1,1);
            this.respawn_timer.element.color = new pc.Color(1,1,1);
            this.respawn_timer.setLocalPosition(149.836, -147.374, 0);
            this.respawn_available_in_text.setLocalPosition(-28.09, -147.374, 0);

            //reset variables
            this.respawnReady = 0;
            this.playerWantsRespawn = 0;
            
            countdownNumber = 5;
            
            //reset timer
            this.timer = 5;

            //stop counting
            this.timerActive = 0;

            //Optional change the players location after clicking. spawn the player afterwards. Should a ui script control spawning? idk but it's effecient and makes sense here right now.
            this.app.fire('player:spawn');
        }

        //If timer has expired, set active to 0 so it will stop counting
        //**
        //Runaway timers could exist if changes are made to this code. Runaway timers = Timers that don't stop counting
        //Put the folowing if statement outside of the "if(this.timerActive[i] == 1)" to prevent that possibility.
        //Will use more time steps. Only needed if timers are set to higher values elsewhere in code.
        //**
        // if(this.timer >= 0)
        // {
        //     //Timer is expired
        //     //timer 5 or more 

        //     console.log("this.timerActive: "+this.timerActive);
        //     console.log("this.timer: "+this.timer);

        //     //Set the timer to not active so that time steps will not be used to count timer
        //     this.timerActive = 0;
        //     console.log("this.timerActive: "+this.timerActive);
        //     console.log("this.timer: "+this.timer);

        //     //set the timer to 0
        //     this.timer = 0;


        //     //The timer has expired
        //     if (this.app.mouse.wasReleased((pc.MOUSEBUTTON_LEFT))) //try wasPressed after
        //     {
        //         console.log("Mouse was released");
        //     }

        // }
    }
    
};

// //when mouse button in pressed down
// Mouse.prototype.onMouseDown = function (event) {
//     // If the left mouse button is pressed, change the cube color to red
//     if (event.button === pc.MOUSEBUTTON_LEFT) {
//         this.entity.render.meshInstances[0].material = this.redMaterial.resource;
//     }

//     // If the middle mouse button is pressed, change the cube color to green
//     if (event.button === pc.MOUSEBUTTON_MIDDLE) {
//         this.entity.render.meshInstances[0].material = this.greenMaterial.resource;
//     }

//     // If the right mouse button is pressed, change the cube color to blue
//     if (event.button === pc.MOUSEBUTTON_RIGHT) {
//         this.entity.render.meshInstances[0].material = this.blueMaterial.resource;
//     }
// };

// swap method called for script hot-reloading
// inherit your script state here
// DeathUi.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/