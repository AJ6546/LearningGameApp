package com.example.gameapp.model;

public class Elements {
    int numberOfElectrons;
    String description;

    public Elements(int numberOfelectrons)
    {
        this.numberOfElectrons =numberOfelectrons;
        switch(this.numberOfElectrons)
        {
            case 1:
                this.description="explosive gas, lightest element\n" +
                        "90% of atoms in the universe, sun and stars, H2O, life's organic molecules";
                break;
            case 2:
                this.description="inert gas, second-lightest element\n" +
                        "nuclear fusion in sun and stars, balloons, lasers, super-cold refrigerant";
                break;
            case 3:
                this.description="lightest metal, soft, reactive\n" +
                        "lightweight aluminum alloys, batteries, impact-resistant ceramic cookware, mood stabilizer";
                break;
            case 4:
                this.description="lightweight metal\n" +
                        "non-sparking copper alloy tools, aerospace, X-ray windows, beryl gems: emeralds and aquamarines";
                break;
            case 5:
                this.description="hard black solid\n" +
                        "borax soap, fertilizer, stiff fibers, sports equipment (golf clubs, tennis rackets, skis), heat-resistant borosilicate glass, semiconductors";
                break;
            case 6:
                this.description="hard diamond, soft graphite\n" +
                        "basis of life's organic molecules, animals, plants, CO2, wood, paper, cloth, plastic, coal, oil";
                break;
            case 7:
                this.description="colorless gas\n" +
                        "78% of air, organic molecules, protein, muscles, DNA, ammonia, fertilizer, explosives (TNT), refrigerants";
                break;
            case 8:
                this.description="colorless gas\n" +
                        "21% of air, H2O, 65% of the human body, organic molecules, blood, breathing, fire, half of Earth's crust, minerals, oxides";
                break;
            case 9:
                this.description="yellowish poison gas, most reactive element\n" +
                        "glowing fluorite, toothpaste, nonstick cookware (Teflon), CFC refrigerants (Freon)";
                break;
            case 10:
                this.description="inert gas\n" +
                        "orange-red neon tubes for advertising signs, lasers, supercold refrigerant";
                break;
            case 11:
                this.description="soft metal, reactive\n" +
                        "salt (NaCl), nerves, baking soda, antacids, lye, soap, soda ash, glass, papermaking, street lamps";
                break;
            case 12:
                this.description="lightweight metal\n" +
                        "chlorophyll in green plants, talc, basalt, aluminum alloys, cars, planes, bikes, flares, sparklers, antacids";
                break;
            case 13:
                this.description="lightweight non-corroding metal\n" +
                        "common metal, kitcheware, cans, foil, machinery, cars, planes, bikes, feldspar, granite, clay, ceramics, corundum, gems";
                break;
            case 14:
                this.description="hard metalloid\n" +
                        "quartz, granite, sand, soil, clay, ceramics, glass, algae, diatoms, semiconductors, computer chips, silicone rubber";
                break;
            case 15:
                this.description="glowing white waxy solid (also red and black forms)\n" +
                        "bones, DNA, energy-storing phosphates (ATP), fertilizer, acids, detergents, matches";
                break;
            case 16:
                this.description="brittle yellow solid\n" +
                        "skin, hair, eggs, onions, garlic, skunks, hot springs, volcanos, gypsum, rubber, acids, papermaking";
                break;
            case 17:
                this.description="greenish poison gas\n" +
                        "salt (NaCl), bleach, stomach acid, disinfectant, drinking water, swimming pools, PVC plastic pipes and bottles";
                break;
            case 18:
                this.description="inert gas\n" +
                        "1% of air, most abundant inert gas on Earth, light bulbs, \"neon\" tubes, lasers, welding gas";
                break;
            case 19:
                this.description="soft metal, reactive\n" +
                        "salts, nerves, nutrient in fruits and vegetables, soap, fertilizer, potash, matches, gunpowder";
                break;
            case 20:
                this.description="soft metal\n" +
                        "bones, teeth, milk, leaves, vegetables, shells, coral, limestone, chalk, gypsum, plaster, mortar, cement, marble, antacids";
                break;
            case 21:
                this.description="soft lightweight metal\n" +
                        "aluminum alloys, racing bikes, stadium lamps, furnace bricks, aquamarines";
                break;
            case 22:
                this.description="strongest lightweight metal, heat-resistant\n" +
                        "aerospace, racing bikes, artificial joints, white paint, blue sapphires";
                break;
            case 23:
                this.description="hard metal\n" +
                        "hard strong resilient steel, structures, vehicles, springs, driveshafts, tools, aerospace, violet sapphires";
                break;
            case 24:
                this.description="hard shiny metal\n" +
                        "stainless steel (Fe-Cr-Ni), kitchenware, nichrome heaters, car trim, paints, recording tape, emeralds and rubies";
                break;
            case 25:
                this.description="hard metal\n" +
                        "hard tough steel, earth movers, rock crushers, rails, plows, axes, batteries, fertilizer, amethysts";
                break;
            case 26:
                this.description="medium-hard metal, magnetic\n" +
                        "steel alloys are mostly iron, structures, vehicles, magnets, Earth's core, red rocks, blood";
                break;
            case 27:
                this.description="hard metal, magnetic\n" +
                        "hard strong steel, cutting tools, turbines, magnets (Al-Ni-Co), blue glass and ceramics, vitamin B-12";
                break;
            case 28:
                this.description="medium-hard metal, magnetic\n" +
                        "stainless steel (Fe-Cr-Ni), kitchenware, nichrome heaters, coins, nicad batteries, Earth's core";
                break;
            case 29:
                this.description="colored metal, conducts heat and electricity well\n" +
                        "wires, cookware, brass (Cu-Zn), bronze (Cu-Sn), coins, pipes, blue crab blood";
                break;
            case 30:
                this.description="non-corroding metal\n" +
                        "galvanized steel, brass (Cu-Zn), batteries, white paint, phosphors in TVs and lamps, fertilizer";
                break;
            case 31:
                this.description="soft metal, melts on a hot day\n" +
                        "semiconductors, light-emitting diodes (LEDs) (GaAs), signal lights, tiny lasers";
                break;
            case 32:
                this.description="brittle metalloid\n" +
                        "semiconductors, transistors, rectifiers, diodes, photocells, lenses, infrared windows";
                break;
            case 33:
                this.description="brittle metalloid\n" +
                        "poisons, semiconductors, light-emitting diodes (LEDs) (GaAs), signal lights, tiny lasers";
                break;
            case 34:
                this.description="brittle gray solid\n" +
                        "semiconductors, photocopiers, laser printers, photocells, red glass, dandruff shampoo, rubber";
                break;
            case 35:
                this.description="dark red liquid\n" +
                        "disinfectant, pools and spas, photo film, flame retardant, leaded gas, sedatives";
                break;
            case 36:
                this.description="inert gas\n" +
                        "high-intensity lamps, headlights, flashlights, lanterns, \"neon\" tubes, lasers";
                break;
            case 37:
                this.description="soft metal, reactive\n" +
                        "atomic clocks, global navigation (GPS), vacuum tube scavenger";
                break;
            case 38:
                this.description="soft metal\n" +
                        "red fireworks, flares, phosphors, nuclear batteries, medical diagnostic tracer, nuclear fallout";
                break;
            case 39:
                this.description="soft metal\n" +
                        "phosphors in color TVs, lasers (YAG, YLF), furnace bricks, high-temperature superconductors";
                break;
            case 40:
                this.description="non-corroding neutron-resistant metal\n" +
                        "chemical pipelines, nuclear reactors, furnace bricks, abrasives, zircon gems";
                break;
            case 41:
                this.description="high-melting-point non-corroding metal\n" +
                        "chemical pipelines, superconductors, magnetic levitation trains, MRI magnets";
                break;
            case 42:
                this.description="high-melting-point metal\n" +
                        "hard steel, cutting tools, drill bits, armor plate, gun barrels, fertilizer";
                break;
            case 43:
                this.description="radioactive, long-lived\n" +
                        "first human-made element, only traces on Earth but found in stars, medical diagnostic tracer";
                break;
            case 44:
                this.description="non-corroding hard metal\n" +
                        "electric contacts, leaf switches, pen tips, catalyst, hydrogen production";
                break;
            case 45:
                this.description="non-corroding hard shiny metal\n" +
                        "lab-ware, reflectors, electric contacts, thermocouples, catalyst, pollution control";
                break;
            case 46:
                this.description="non-corroding hard metal, absorbs hydrogen\n" +
                        "lab-ware, electric contacts, dentistry, catalyst, pollution control";
                break;
            case 47:
                this.description="soft shiny metal, conducts electricity best of all elements\n" +
                        "jewelry, silverware, coins, dentistry, photo film";
                break;
            case 48:
                this.description="non-corroding soft metal, toxic\n" +
                        "electroplated steel, nicad batteries, red and yellow paints, fire sprinklers";
                break;
            case 49:
                this.description="soft metal\n" +
                        "solders, glass seals, glass coatings, liquid crystal displays (LCD), semiconductors, diodes, photocells";
                break;
            case 50:
                this.description="non-corroding soft metal\n" +
                        "solders, plated food cans, bronze (Cu-Sn), pewter cups, glassmaking, fire sprinklers";
                break;
            case 51:
                this.description="brittle metalloid\n" +
                        "solders, lead hardener, batteries, bullet, semiconductors, photocells, matches, flame retardant";
                break;
            case 52:
                this.description="brittle metalloid\n" +
                        "alloys, semiconductors, photocopiers, computer disk, thermo-electric coolers and generators";
                break;
            case 53:
                this.description="violet-black solid\n" +
                        "disinfectant for wounds and drinking water, added to salt to prevent thyroid disease, photo film";
                break;
            case 54:
                this.description="inert gas\n" +
                        "high-intensity lamps, headlights, stadium lamps, projectors, strobes, lasers, spacecraft ion engines";
                break;
            case 55:
                this.description="soft metal, melts on a hot day, reactive, largest stable atoms\n" +
                        "atomic clocks, global navigation (GPS), vacuum tube scavenger";
                break;
            case 56:
                this.description="soft metal, absorbs X-rays\n" +
                        "stomach X-ray contrast enhancer, green fireworks, whitener and filler for paper";
                break;
            case 57:
                this.description="soft metal\n" +
                        "optical glass, telescope eyepieces, camera lenses, lighter flints, arc lamps";
                break;
            case 58:
                this.description="soft metal\n" +
                        "most abundant rare earth metal, lighter flints, arc lamps, gas lamp mantles, self-cleaning ovens, glass polishing";
                break;
            case 59:
                this.description="soft metal\n" +
                        "torch-workers' didymium eyeglasses, lighter flints, arc lamps, magnets, yellow glass";
                break;
            case 60:
                this.description="soft metal\n" +
                        "strong magnets (Nd-Fe-B), electric motors, speakers and headphones, lasers, lighter flints";
                break;
            case 61:
                this.description="radioactive, long-lived\n" +
                        "human-made, small traces in nature, luminous dials, sheet thickness gauges";
                break;
            case 62:
                this.description="soft metal\n" +
                        "magnets (Sm-Co), electric motors, speakers and headphones, infrared sensors, infrared-absorbing glass";
                break;
            case 63:
                this.description="soft metal\n" +
                        "phosphors in color TVs and trichromatic fluorescent lamps, luminous paint, lasers";
                break;
            case 64:
                this.description="soft metal, best neutron absorber, magnetic\n" +
                        "magnetic resonance imaging (MRI) contrast enhancer, phosphors, neutron radiography";
                break;
            case 65:
                this.description="soft metal\n" +
                        "phosphors in color TVs and trichromatic fluorescent lamps, computer disks, magnetostrictive \"smart\" materials (Fe-Dy-Tb) (Terfenol-D)";
                break;
            case 66:
                this.description="soft metal\n" +
                        "nuclear control rods, MRI phosphors, computer disks, magnetostrictive \"smart\" materials (Fe-Dy-Tb) (Terfenol-D)";
                break;
            case 67:
                this.description="soft metal\n" +
                        "infrared lasers, laser surgery, eye-safe laser rangefinders, computer disks, yellow glass filters";
                break;
            case 68:
                this.description="soft metal\n" +
                        "fiber optic signal amplifiers, infrared lasers, laser surgery, pink glass, sunglasses, vanadium alloys";
                break;
            case 69:
                this.description="soft metal\n" +
                        "rarest stable rare earth metal, infrared lasers, laser surgery, phosphors";
                break;
            case 70:
                this.description="soft metal\n" +
                        "fiber optic signal amplifiers, infrared fiber lasers, stainless steel alloys";
                break;
            case 71:
                this.description="soft metal, densest and hardest rare earth metal\n" +
                        "cancer-fighting photodynamic (light-activated) medicine";
                break;
            case 72:
                this.description="non-corroding metal, absorbs neutrons\n" +
                        "nuclear reactor control rods in submarines, plasma torch electrodes";
                break;
            case 73:
                this.description="high-melting-point non-corroding metal\n" +
                        "lab-ware, surgical tools, artificial joints, capacitors, mobile phones";
                break;
            case 74:
                this.description="highest-melting-point metal, dense\n" +
                        "filaments in lamps and TVs, cutting tools, abrasives, thermocouples";
                break;
            case 75:
                this.description="high-melting-point dense metal\n" +
                        "rocket engines, heater coils, laboratory filaments, electric contacts, thermocouples, catalyst";
                break;
            case 76:
                this.description="non-corroding high-melting-point hard metal, densest element (same as Ir)\n" +
                        "electric contacts, pen tips, needles, fingerprint powder";
                break;
            case 77:
                this.description="non-corroding hard metal, densest element (same as Os)\n" +
                        "labware, spark plugs, pen tips, needles";
                break;
            case 78:
                this.description="non-corroding dense metal\n" +
                        "labware, spark plugs, catalyst, pollution control, petroleum cracking, processing fats";
                break;
            case 79:
                this.description="most malleable element, dense non-tarnishing colored metal\n" +
                        "jewelry, coins, ultra-thin gold leaf, electric contacts";
                break;
            case 80:
                this.description="liquid metal, toxic\n" +
                        "thermometers, barometers, thermostats, street lamps, fluorescent lamps, dentistry";
                break;
            case 81:
                this.description="soft metal, toxic\n" +
                        "low-melting-point mercury alloys, low-temperature thermometers, undersea lamps, photocells";
                break;
            case 82:
                this.description="soft metal, toxic\n" +
                        "low-melting-point mercury alloys, low-temperature thermometers, undersea lamps, photocells";
                break;
            case 83:
                this.description="low melting point, brittle metal\n" +
                        "solders, fuses, fire sprinklers (plugs melt when hot), cosmetics pigment";
                break;
            case 84:
                this.description="radioactive, long-lived\n" +
                        "first radioactive element found, small traces in nature, anti-static brushes, tobacco";
                break;
            case 85:
                this.description="radioactive, short-lived\n" +
                        "small traces in nature, cancer medicine";
                break;
            case 86:
                this.description="radioactive gas, short-lived\n" +
                        "environmental hazard, surgical implants for cancer treatment";
                break;
            case 87:
                this.description="radioactive, short-lived, atoms larger than cesium\n" +
                        "small traces in nature, studied in laser atom traps";
                break;
            case 88:
                this.description="radioactive, long-lived\n" +
                        "luminous watches (now banned), medical radon production, radiography, rad-waste";
                break;
            case 89:
                this.description="radioactive, long-lived\n" +
                        "small traces in nature, cancer medicine, neutron source, radwaste";
                break;
            case 90:
                this.description="radioactive, long-lived\n" +
                        "most abundant radioactive element, nuclear reactor fuel, gas lamp mantles, tungsten filaments, radwaste";
                break;
            case 91:
                this.description="radioactive, long-lived\n" +
                        "small traces in nature, no uses, radwaste";
                break;
            case 92:
                this.description="radioactive, long-lived, dense\n" +
                        "nuclear reactor fuel, nuclear weapons, counterweights, armor-piercing bullets";
                break;
            case 93:
                this.description="radioactive, long-lived\n" +
                        "small traces in nature, neutron detectors, dosimeters, possibly nuclear weapons, radwaste";
                break;
            case 94:
                this.description="radioactive, long-lived\n" +
                        "small traces in nature, nuclear reactor fuel, spacecraft power, nuclear weapons";
                break;
        }
    }
    public int getNumberOfElectrons() {
        return numberOfElectrons;
    }

    public void setNumberOfElectrons(int numberOfElectrons) {
        this.numberOfElectrons = numberOfElectrons;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
