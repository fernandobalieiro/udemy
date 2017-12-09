new Vue({
    el: '#app',
    data: {
        gameOn: false,
        myHealth: 100,
        monsterHealth: 100,
        actionResults: [],
        myAttackResult: 0,
        monsterAttackResult: 0,
        healingTimes: 0
    },
    computed: {
        MAX_HEALTH: () => 100,
        MIN_DAMAGE: () => 5,
        MAX_DAMAGE: () => 10,
        MIN_DAMAGE_SPECIAL_ATTACK: () => 10,
        MAX_DAMAGE_SPECIAL_ATTACK: () => 40,
        MIN_HEALING: () => 5,
        MAX_HEALING: () => 10,
        MAX_HEALING_TIMES: () => 5
    },
    watch: {
        monsterHealth: function() {
            if (this.monsterHealth <= 0) {
                if (confirm('You won! New game?')) {
                    this.newGame();
                }
            }
        },
        myHealth: function() {
            if (this.myHealth <= 0) {
                if (confirm('You lost! New game?')) {
                    this.newGame();
                }
            }   
        }
    },
    methods: {
        attack: function() {
            this.myAttack(this.MIN_DAMAGE, this.MAX_DAMAGE);
            this.monsterAttack(this.MIN_DAMAGE, this.MAX_DAMAGE);
        },
        specialAttack: function() {
            this.myAttack(this.MIN_DAMAGE_SPECIAL_ATTACK, this.MAX_DAMAGE_SPECIAL_ATTACK);
            this.monsterAttack(this.MIN_DAMAGE, this.MAX_DAMAGE);
        },
        myAttack: function(minDamage, maxDamage) {
            this.myAttackResult = this.getRandom(minDamage, maxDamage);
            this.pushResult('player-turn', 'PLAYER HITS MONSTER FOR ', this.myAttackResult);
            this.monsterHealth -= this.myAttackResult;
        },
        monsterAttack: function(minDamage, maxDamage) {
            this.monsterAttackResult = this.getRandom(minDamage, maxDamage);
            this.pushResult('monster-turn', 'MONSTER HITS PLAYER FOR ', this.monsterAttackResult);
            this.myHealth -= this.monsterAttackResult;
        },
        getRandom: function(min, max) {
            min = Math.ceil(min);
            max = Math.floor(max);
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        pushResult: function(clazz, message, damage) {
            var resultAction = {
                resultClass: clazz,
                resultMessage: '[' + new Date() + '] ' + message + damage,
            };

            this.actionResults.push(resultAction);
        },
        heal: function() {
            if (this.healingTimes < this.MAX_HEALING_TIMES) {
                var plusHealth = this.getRandom(this.MIN_HEALING, this.MAX_HEALING);
                if (this.myHealth + plusHealth <= this.MAX_HEALTH) {
                    this.myHealth += plusHealth;
                } else {
                    this.myHealth = this.MAX_HEALTH;
                }
                this.healingTimes++;
            } else {
                alert('Max healing times reached!');
            }
        },
        newGame: function() {
            this.gameOn = false;
            this.actionResults = [];
            this.myHealth = this.MAX_HEALTH;
            this.monsterHealth = this.MAX_HEALTH;
            this.myAttackResult = 0;
            this.monsterAttackResult = 0;
        }
    }
});