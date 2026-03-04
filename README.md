# SDnD Spring 2026 - EUI

This repository is created for managing the source code of SDnD lab sessions. Structured as a Maven project.

The organization of the project is hierarchical. Each topic addressed during the semester is encapsulated within its own distinct sub package. Every sub package includes its own `Main.java` entry point, before and after redesign.
We created a root launcher located at `eg.edu.eui.sdnd.sp26.Main`. This class acts as a master run file tying all modules together through a numbered console menu.
