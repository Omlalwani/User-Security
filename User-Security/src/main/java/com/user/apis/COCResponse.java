package com.user.apis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class COCResponse{
    private String tag;
    private String name;
    private int townHallLevel;
    private int expLevel;
    private int trophies;
    private int bestTrophies;
    private int warStars;
    private int attackWins;
    private int defenseWins;
    private int builderHallLevel;
    private int builderBaseTrophies;
    private int bestBuilderBaseTrophies;
    private String role;
    private String warPreference;
    private int donations;
    private int donationsReceived;
    private int clanCapitalContributions;
    private Clan clan;
    private League league;
    private BuilderBaseLeague builderBaseLeague;
    private List<Achievement> achievements;
    private PlayerHouse playerHouse;
    private List<Label> labels;
    private List<Troop> troops;
    private List<Hero> heroes;
    private List<HeroEquipment> heroEquipment;
    private List<Spell> spells;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Spell{
        private String name;
        private int level;
        private int maxLevel;
        private String village;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Troop{
        private String name;
        private int level;
        private int maxLevel;
        private String village;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Achievement{
        private String name;
        private int stars;
        private int value;
        private int target;
        private String info;
        private String completionInfo;
        private String village;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BuilderBaseLeague{
        private int id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clan{
        private String tag;
        private String name;
        private int clanLevel;
        private BadgeUrls badgeUrls;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BadgeUrls{
            private String small;
            private String large;
            private String medium;
        }

    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hero{
        private String name;
        private int level;
        private int maxLevel;
        private List<Equipment> equipment;
        private String village;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Equipment{
            private String name;
            private int level;
            private int maxLevel;
            private String village;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HeroEquipment{
        private String name;
        private int level;
        private int maxLevel;
        private String village;
    }




    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Label{
        private int id;
        private String name;
        private IconUrls iconUrls;


        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class IconUrls{
            private String small;
            private String tiny;
            private String medium;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class League{
        private int id;
        private String name;
        private IconUrls iconUrls;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class IconUrls{
            private String small;
            private String tiny;
            private String medium;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerHouse{
        private List<Element> elements;


        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Element{
            private String type;
            private int id;
        }
    }

}
