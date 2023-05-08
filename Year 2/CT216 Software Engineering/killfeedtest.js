//This would be the script that has the player names of who just killed who. e.g. the damage script
//This script will create a new killfeed entry with the keyboard input "k".
//documentation for keyboard events: https://developer.playcanvas.com/en/tutorials/keyboard-input/#%3Ccode%3Eispressed%3C/code%3E-vs-%3Ccode%3Ewaspressed%3C/code%3E
//Events https://developer.playcanvas.com/en/user-manual/scripting/communication/

var Killfeedtest = pc.createScript('killfeedtest');


Killfeedtest.attributes.add('nameListCounter', {
    type: 'number',
    default: 0
});

// initialize code called once per entity
Killfeedtest.prototype.initialize = function()
{
    //Other method keyboard input: uses events (not used here because this does not happen inside update method but I left it as commented code)
    //Listen for events on the keyboard device
    //this.app.keyboard.on(pc.EVENT_KEYDOWN, this.onKeyDown, this);
    //this.app.keyboard.on(pc.EVENT_KEYUP, this.onKeyUp, this);

    this.player1 = "unamedPlayer1";
    this.player2 = "unamedPlayer2";
    this.nameListCounter = 0;
};

// update code called every frame
Killfeedtest.prototype.update = function(dt)
{       
    //listen for pressing k
    if(this.app.keyboard.wasPressed(pc.KEY_K))
    {
        //This test will do 9 different kills. Every time you press k it will be a new kill.
        //Get the player names
        switch(this.nameListCounter)
        {
            case 0:
                this.player1 = "1 fire hazard"; //the killer
                this.player2 = "sini"; //the guy who died
                break;
            case 1:
                this.player1 = "2 sini";
                this.player2 = "roblox";
                break;
            case 2:
                this.player1 = "3 sini";
                this.player2 = "xantares";
                break;
            case 3:
                this.player1 = "4 roblox";
                this.player2 = "sini";
                break;
            case 4:
                this.player1 = "5 fire hazard";
                this.player2 = "roblox";
                break;
            case 5:
                this.player1 = "6 roblox";
                this.player2 = "fire hazard";
                break;
            case 6:
                this.player1 = "7 fire hazard";
                this.player2 = "sini";
                break;
            case 7:
                this.player1 = "8 fire hazard";
                this.player2 = "roblox";
                break;
            case 8:
                this.player1 = "9 fire hazard";
                this.player2 = "fire hazard";
                break;
            case 9:
                this.player1 = "end of test 1";
                this.player2 = "end of test 2";
                break;
            case 10:
                this.player1 = "resetting test";
                this.player2 = "press k";
                this.nameListCounter = -1;
                break;
            case 100:
                this.player1 = "stop pressing k wyd?";
                this.player2 = "<3";
                break;
            default:
                this.player1 = "default";
                this.player2 = "default";
        }
        this.nameListCounter++;


        //A kill has just happened. The following code displays the kill on the killfeed.

        // this.player1 = "fire hazard"; //the killer
        // this.player2 = "sini"; //the guy who died

        //send a killfeed entry to the killfeed
        this.app.fire('killfeed:add', this.player1, this.player2);
    }
};



//Event based keyboard
// Killfeedtest.prototype.onKeyDown = function (event)
// {
//     if (event.key === pc.KEY_K)
//     {
//         //do something
//     }

//     // For example: when the space bar is pressed this scrolls the window.
//     // Calling preventDefault() on the original browser event stops this.
//     event.event.preventDefault();
// };

// Killfeedtest.prototype.onKeyUp = function (event)
// {
//     if (event.key === pc.KEY_K)
//     {
//         //do something
//     }

//     // For example: when the space bar is pressed this scrolls the window.
//     // Calling preventDefault() on the original browser event stops this.
//     event.event.preventDefault();
// };



// swap method called for script hot-reloading
// inherit your script state here
// Killfeedtest.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/