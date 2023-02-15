# Ant-and-hornet-simulator

## Description

Simulation of ants and hornets, that's written in Java Swing.

### Rules
Ants are moving randomly across the map trying to find food, hornets try to kill them and flowers scares away hornets. Ants collect food to their nest. Nest spawns another ant that has chance to become solider ant that's capable of killing hornets, when certain amount of food has been collected. When ant sees nearby hornet it runs directly into the nearest flower to hide from hornets.

### How it looks
![2023-02-15 16-09-26](https://user-images.githubusercontent.com/77162184/219067505-236352d6-abde-4e45-884a-297f8b7ab3d1.gif)
### Test mode
We implement test mode to gather the data to draw some conclusions about the simulation.

We started with this setup:

![image](https://user-images.githubusercontent.com/77162184/219068497-54352cf0-5044-476f-b665-6e9f5c82f5e9.png)

Tests depending on the number of hornets:

![image](https://user-images.githubusercontent.com/77162184/219069033-6ccd8672-32ab-4792-b7c9-0889d519006e.png)

Population of hornets:

![image](https://user-images.githubusercontent.com/77162184/219069087-51e9b02e-ffe8-4ff5-a24a-0058dc0c9f9a.png)

Population of ants:

![image](https://user-images.githubusercontent.com/77162184/219069195-dacf102b-ddbc-4207-8836-4480be926393.png)

Amount of food:

![image](https://user-images.githubusercontent.com/77162184/219069261-0f145904-4ccb-4710-b50f-a6cbef5a1f24.png)

From the charts we clearly see that:
- population of hornets no matter of begging size still decreases, but a bit slower
- population of ants decreases
- ants gather less food

