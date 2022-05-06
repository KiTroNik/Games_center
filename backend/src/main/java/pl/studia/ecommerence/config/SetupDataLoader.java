package pl.studia.ecommerence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.model.Role;
import pl.studia.ecommerence.model.User;
import pl.studia.ecommerence.repository.ProductRepository;
import pl.studia.ecommerence.repository.RoleRepository;
import pl.studia.ecommerence.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setEmail("admin@admin.com");
        user.setRoles(List.of(adminRole));
        userRepository.save(user);

        productRepository.save(new Product("Observer", "SYSTEM REDUX\n" +
                "Veteran Observers can dive deeper into this dystopian reality thanks to three brand-new side cases to solve. “Errant Signal”, “Her Fearful Symmetry” and “It Runs in the Family” add another layer of depth to the game’s story while exploring thought-provoking themes of a future that soon might become our present.\n" +
                "\n" +
                "On top of that, System Redux offers expanded gameplay, which includes new game mechanics, new secrets to find, redesigned stealth, additional neural interrogations, and quality-of-life improvements made with the help of the Observer community.\n" +
                "\n" +
                "Newcomers get the chance to experience one of the most thrilling cyberpunk stories in all its next-gen glory: boasting 4K resolution, upgraded textures, new animations, models and effects, and transformed with jaw-dropping Ray-Tracing and HDR lighting.\n" +
                "\n" +
                "WELCOME TO THE FUTURE\n" +
                "The year is 2084. The future has turned out much darker than anyone could imagine. First, there was the Nanophage. A digital plague that killed thousands upon thousands of those who chose to augment their minds and bodies.\n" +
                "\n" +
                "Then came the War, leaving both the West and the East decimated and shattered. With no one left to seize power, corporations took over and forged their own crooked empires.\n" +
                "\n" +
                "You are a tool of corporate oppression. Feared and despised, you hack into the darkest corners of your suspects’ minds. You creep into their dreams, expose their fears, and extract whatever your investigation may require.\n" +
                "\n" +
                "You are an Observer.\n" +
                "\n" +
                "BECOME A NEURAL DETECTIVE\n" +
                "You play as Daniel Lazarski, an elite investigator of the future portrayed by late cyberpunk icon Rutger Hauer. As an Observer, you hack into the minds of suspects to extract clues and evidence. Anything they felt, thought, or remembered can and will be used to solve your case.\n" +
                "\n" +
                "EAT THEIR DREAMS\n" +
                "Using a device known as the Dream Eater, hack into the minds of the dead and dying to relive their final moments, to explore their fears and obsessions. Delve into these twisted neural mazes and search for clues that will help you find the elusive killer. But beware: the deeper you go, the greater the risk of losing your own sanity.\n" +
                "\n" +
                "TAKE IN THE ATMOSPHERE OF 2084\n" +
                "Enter a world ravaged by war and cyber-plague, where life is cheap and hope is scarce. Where most people will do anything to escape their grim existence. Virtual realities, mind-altering drugs, neural implants - pick your poison and drift away into a blissful oblivion. But remember, no matter how deep you go, you can never hide from an Observer.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202009/2214/ErqnwUhRIdUHcJifL4ownssD.png?w=440&thumb=false"));
        productRepository.save(new Product("Marvel's Spider-Man: Miles Morales", "In the latest adventure in the Marvel's Spider-Man universe, teenager Miles Morales is adjusting to his new home while following in the footsteps of his mentor, Peter Parker, as a new Spider-Man. But when a fierce power struggle threatens to destroy his new home, the aspiring hero realises that with great power, there must also come great responsibility. To save all of Marvel's New York, Miles must take up the mantle of Spider-Man and own it.\n" +
                "\n" +
                "If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202008/1020/T45iRN1bhiWcJUzST6UFGBvO.png?w=440&thumb=false"));
        productRepository.save(new Product("Borderlands 3", "The Super Deluxe Edition includes the Season Pass and Deluxe bonus content.\n" +
                "\n" +
                "Season Pass:\n" +
                "\n" +
                "• 4 campaign DLC packs featuring new stories, missions and challenges\n" +
                "• Butt Stallion weapon skin, weapon trinket, and grenade mod\n" +
                "\n" +
                "Deluxe bonus content:\n" +
                "\n" +
                "• Retro Cosmetic Pack\n" +
                "• Neon Cosmetic Pack\n" +
                "• Gearbox Cosmetic Pack\n" +
                "• Toy Box Weapons Pack\n" +
                "• Equippable XP & Loot Drop Boost Mods\n" +
                "\n" +
                "The original shooter-looter returns, packing bazillions of guns and an all-new mayhem-fueled adventure! Blast through new worlds and enemies as one of four brand new Vault Hunters – the ultimate treasure-seeking badasses of the Borderlands, each with deep skill trees, abilities and customization. Play solo or with friends to take on insane enemies, score loads of loot and save your home from the most ruthless cult leaders in the galaxy.\n" +
                "\n" +
                "If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202010/2323/p50N4PBK9rNanGYKFecTvac5.png?w=440&thumb=false"));
        productRepository.save(new Product("Mortal Kombat 11", "MK is back and better than ever in the next evolution of the iconic franchise.\n" +
                "\n" +
                "The Mortal Kombat 11 Standard Edition includes:\n" +
                "• Main Game\n" +
                "\n" +
                "The all new Custom Character Variations give you unprecedented control of your fighters to make them your own. The new graphics engine showcases every skull-shattering, eye-popping moment, bringing you so close to the fight you can feel it. Featuring a roster of new and returning Klassic Fighters, Mortal Kombat's best-in-class cinematic story mode continues the epic saga over 25 years in the making.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202009/0123/bF1qmEL5RM6aMfL0oLcxRe8B.png?w=440&thumb=false"));
        productRepository.save(new Product("HITMAN 3", "This digital version of HITMAN 3 includes PlayStation®4 and PlayStation®5 versions of the game.\n" +
                "Take on the most important contracts of Agent 47's career at 60 FPS and in 4K resolution on PlayStation®5\n" +
                "\n" +
                "Death Awaits. Agent 47 returns in HITMAN 3, the dramatic conclusion to the World of Assassination trilogy.\n" +
                "\n" +
                "The HITMAN 3 - Standard Edition includes access to the following locations:\n" +
                "- Dubai\n" +
                "- Dartmoor\n" +
                "- Berlin\n" +
                "- Chongqing\n" +
                "- Mendoza\n" +
                "- Carpathian Mountains", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202007/3011/XX85ZND1RuA13iphoE1Qb7ex.png?w=440&thumb=false"));
        productRepository.save(new Product("Control: Ultimate Edition", "Experience true next-generation gaming with increased visual fidelity through 4K graphics, ray-tracing and a 60fps Performance mode.\n" +
                "\n" +
                "A corruptive presence has invaded the Federal Bureau of Control…Only you have the power to stop it. The world is now your weapon in an epic fight to annihilate an ominous enemy through deep and unpredictable environments. Containment has failed, humanity is at stake. Will you regain control?\n" +
                "\n" +
                "Winner of over 80 awards, Control is a visually stunning third-person action-adventure that will keep you on the edge of your seat. Blending open-ended environments with the signature world-building and storytelling of renowned developer, Remedy Entertainment, Control presents an expansive and intensely gratifying gameplay experience.\n" +
                "\n" +
                "Key Features\n" +
                "\n" +
                "Uncover the mysteries.\n" +
                "Can you handle the bureau’s dark secrets? Unfold an epic supernatural struggle, filled with unexpected characters and bizarre events, as you search for your missing brother, and discover the truth that has brought you here.\n" +
                "\n" +
                "Everything is your weapon.\n" +
                "Unleash destruction through transforming weaponry and telekinetic powers. Discover new ways to annihilate your enemies as you harness powerful abilities to turn everything around you into a lethal weapon.\n" +
                "\n" +
                "Explore a hidden world.\n" +
                "Delve deep into the ominous expanses of a secretive government agency. Explore the Bureau’s shifting environments only to discover that there is always more than meets the eye…\n" +
                "\n" +
                "Fight for control.\n" +
                "Battle a relentless enemy through exciting missions and challenging boss fights to earn powerful upgrades that maximize abilities and customize your weaponry.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202008/2111/hvVTsd8akckaGtN2eZ3yIuwc.png?w=440&thumb=false"));
        productRepository.save(new Product("Crash Bandicoot 4", "It’s About Time - for a brand-wumping new Crash Bandicoot™ game! Crash fourward into a time shattered adventure with your favourite marsupials.\n" +
                "\n" +
                "Neo Cortex and N. Tropy are back at it again and launching an all-out assault on not just this universe, but the entire multiverse! Crash and Coco are here to save the day by reuniting the four Quantum Masks and bending the rules of reality.\n" +
                "\n" +
                "New abilities? Check. More playable characters? Yep. Alternate dimensions? Obviously. Ridonkulous bosses? For sure. Same awesome sauce? You bet your sweet jorts. Wait, are they actually jorts? Not in this universe!", 10.0, "https://image.api.playstation.com/vulcan/img/rnd/202111/1918/za4E5H88KMQDhjJdVtMdKdD9.png?w=440&thumb=false"));
        productRepository.save(new Product("It Takes Two", "Embark on the craziest journey of your life in It Takes Two, a genre-bending platform adventure created purely for co-op. Invite a friend to join for free with Friend’s Pass** and work together across a huge variety of gleefully disruptive gameplay challenges.\n" +
                "\n" +
                "Master unique and connected character abilities in every new level. Help each other through unexpected obstacles and laugh-out-loud moments. Embrace the heartfelt story of a fractured relationship.\n" +
                "\n" +
                "It Takes Two is developed by Hazelight, the industry-leader of cooperative play.\n" +
                "\n" +
                "KEY FEATURES:\n" +
                "PURE CO-OP PERFECTION – Invite a friend to join for free with Friend’s Pass**\n" +
                "\n" +
                "GLEEFULLY DISRUPTIVE GAMEPLAY – From rampaging vacuum cleaners to suave love gurus – with It Takes Two, you never know what you’re up against next.\n" +
                "\n" +
                "A UNIVERSAL TALE OF RELATIONSHIPS – Discover a touching and heartfelt story you’ll treasure – together!", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202012/0815/7CRynuLSAb0vysSC4TmZy5e4.png?w=440&thumb=false"));
        productRepository.save(new Product("Disco Elysium", "Disco Elysium - The Final Cut is the definitive edition of the groundbreaking role playing game. You’re a detective with a unique skill system at your disposal and a whole city block to carve your path across. Interrogate unforgettable characters, crack murders, or take bribes. Become a hero or an absolute disaster of a human being.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202102/1214/gcMVHVG82HnDo0A6WHl6WR6H.png?w=440&thumb=false"));
        productRepository.save(new Product("Resident Evil Village", "Experience survival horror like never before in the eighth major installment in the storied Resident Evil franchise - Resident Evil Village.\n" +
                "\n" +
                "Set a few years after the horrifying events in the critically acclaimed Resident Evil 7 biohazard, the all-new storyline begins with Ethan Winters and his wife Mia living peacefully in a new location, free from their past nightmares. Just as they are building their new life together, tragedy befalls them once again.\n" +
                "\n" +
                "This pack includes the following content:\n" +
                "• Resident Evil Village\n" +
                "• Resident Evil Re:Verse\n" +
                "\n" +
                "Resident Evil Re:Verse is an online-only multiplayer title that requires a PlayStation® Plus account in order to play.\n" +
                "\n" +
                "Details of the operational period for Resident Evil Re:Verse, including commencement of availability and any potential announcements relating to the end of service, can be found on the official website:\n" +
                "\n" +
                "https://www.residentevil.com/reverse/\n" +
                "\n" +
                "Please note that, depending on the timing of your purchase of Resident Evil Village, Resident Evil Re:Verse may already no longer be available, and/or the title may become available through other means in the future.\n" +
                "\n" +
                "\n" +
                "Resident Evil Village:\n" +
                "\n" +
                "Voice:\n" +
                "\n" +
                "English, French (France), German, Italian, Japanese, Portuguese (Brazil), Russian, Spanish\n" +
                "\n" +
                "Screen Languages:\n" +
                "\n" +
                "Arabic, English, French (France), German, Italian, Japanese, Portuguese (Brazil), Russian, Spanish\n" +
                "\n" +
                "Resident Evil Re:Verse:\n" +
                "\n" +
                "Voice:\n" +
                "\n" +
                "English\n" +
                "\n" +
                "Screen Languages:\n" +
                "\n" +
                "Arabic, Chinese (Simplified), Chinese (Traditional), English, French (France), German, Italian, Japanese, Korean, Portuguese (Brazil), Russian, Spanish, Thai", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202101/0812/FkzwjnJknkrFlozkTdeQBMub.png?w=440&thumb=false"));
        productRepository.save(new Product("Metro Exodus", "Metro Exodus is an epic, story-driven first person shooter from 4A Games that blends deadly combat and stealth with exploration and survival horror in one of the most immersive game worlds ever created.\n" +
                "\n" +
                "Flee the shattered ruins of dead Moscow and embark on an epic, continent-spanning journey across post-apocalyptic Russia in the greatest Metro adventure yet.\n" +
                "\n" +
                "Explore the Russian wilderness in vast, non-linear levels and follow a thrilling story-line inspired by the novels of Dmitry Glukhovsky that spans an entire year through spring, summer and autumn to the depths of nuclear winter.", 10.0, "https://image.api.playstation.com/vulcan/img/rnd/202010/2814/jhNIE3yH1SNEe9CmxTVHAdme.png?w=440&thumb=false"));
        productRepository.save(new Product("DOOM Eternal", "Hell’s armies have invaded Earth. Become the Slayer in an epic single-player campaign to conquer demons across dimensions and stop the final destruction of humanity. The only thing they fear... is you.\n" +
                "\n" +
                "Experience the ultimate combination of speed and power in DOOM Eternal - the next leap in push-forward, first-person combat.\n" +
                "\n" +
                "Enter BATTLEMODE\n" +
                "A new 2 versus 1 multiplayer experience. A fully-armed DOOM Slayer faces off against two player-controlled demons, fighting it out in a best-of-five round match of intense first-person combat.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202010/0114/ERNPc4gFqeRDG1tYQIfOKQtM.png?w=440&thumb=false"));
        productRepository.save(new Product("A Plague Tale: Innocence", "Follow the critically acclaimed tale of young Amicia and her little brother Hugo, in a heartrending journey through the darkest hours of history. Hunted by Inquisition soldiers and surrounded by unstoppable swarms of rats, Amicia and Hugo will come to know and trust each other. As they struggle to survive against overwhelming odds, they will fight to find purpose in this brutal, unforgiving world.\n" +
                "\n" +
                "Enjoy 4K resolution, 60FPS, highly improved visuals and fast loading on PlayStation®5.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202106/2216/uFqCClqXSIsEFIo3ktJdZm7H.png?w=440&thumb=false"));
        productRepository.save(new Product("Assassin's Creed Valhalla", "Become Eivor, a legendary Viking warrior raised on tales of battle and glory. Raid your enemies, grow your settlement, and build your political power in a dynamic open world.\n" +
                "\n" +
                "Strike fear into the hearts of your enemies with the Ultimate Pack cosmetic items:\n" +
                "- The Berserker Gear Pack includes the Bearded Axe, a new Raven skin, the ferocious Hati Wolf Mount and more.\n" +
                "- The Berserker Longship Pack includes a majestic Longship with a unique figurehead and sails.\n" +
                "- The Berserker Settlement Pack includes new ways to customize your settlement in England.\n" +
                "- A Set of Runes to sharpen weapons or improve gear.\n" +
                "\n" +
                "If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202008/1318/8XGEPtD1xoasK0FYkYNcCn1z.png?w=440&thumb=false"));
        productRepository.save(new Product("Hades", "Defy the god of the dead as you hack and slash out of the Underworld in this rogue-like dungeon crawler from the creators of Bastion, Transistor, and Pyre.\n" +
                "\n" +
                "BATTLE OUT OF HELL\n" +
                "As the immortal Prince of the Underworld, you'll wield the powers and mythic weapons of Olympus to break free from the clutches of the god of the dead himself, while growing stronger and unraveling more of the story with each unique escape attempt.\n" +
                "\n" +
                "UNLEASH THE FURY OF OLYMPUS\n" +
                "The Olympians have your back! Meet Zeus, Athena, Poseidon, and many more, and choose from their dozens of powerful Boons that enhance your abilities. There are thousands of viable character builds to discover as you go.\n" +
                "\n" +
                "BEFRIEND GODS, GHOSTS, AND MONSTERS\n" +
                "A fully-voiced cast of colorful, larger-than-life characters is waiting to meet you! Grow your relationships with them, and experience thousands of unique story events as you learn about what's really at stake for this big, dysfunctional family.\n" +
                "\n" +
                "BUILT FOR REPLAYABILITY\n" +
                "New surprises await each time you delve into the ever-shifting Underworld, whose guardian bosses will remember you. Use the powerful Mirror of Night to grow permanently stronger, and give yourself a leg up the next time you run away from home.\n" +
                "\n" +
                "NOTHING IS IMPOSSIBLE\n" +
                "Permanent upgrades mean you don't have to be a god yourself to experience the exciting combat and gripping story. Though, if you happen to be one, crank up the challenge and get ready for some white-knuckle action that will put your well-practiced skills to the test.\n" +
                "\n" +
                "SIGNATURE SUPERGIANT STYLE\n" +
                "The rich, atmospheric presentation and unique melding of gameplay and narrative that's been core to Supergiant's games is here in full force: spectacular hand-painted environments and a blood-pumping original score bring the Underworld to life.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202104/0517/9AcM3vy5t77zPiJyKHwRfnNT.png?w=440&thumb=false"));
        productRepository.save(new Product("The Medium", "Discover a dark mystery only a medium can solve. Explore the real world and the spirit world at the same time. Use your psychic abilities to solve puzzles spanning both worlds, uncover deeply disturbing secrets, and survive encounters with The Maw - a monster born from an unspeakable tragedy..\n" +
                "\n" +
                "The Medium is a third-person psychological horror game that features innovative dual-reality gameplay and an original soundtrack co-composed by Arkadiusz Reikowski and Akira Yamaoka. Get even more immersed in its uncanny atmosphere thanks to the extensive support for the unique features of your DualSense controller.\n" +
                "\n" +
                "BECOME A MEDIUM\n" +
                "Wield unique psychic abilities reserved for those with the gift. Travel between the realities or explore them both at the very same time. Use the Out of Body experience to investigate places where your real-world self can’t go. Create energy shields and deliver powerful spirit blasts to survive the spirit world and its otherworldly dangers.\n" +
                "\n" +
                "SEE WHAT’S HIDDEN\n" +
                "Delve deep into a mature and morally ambiguous story, where nothing is what it seems and everything has another side. As a medium you see, hear and experience more than others, and with every new perspective you will change your perception on what happened at the Niwa resort.\n" +
                "\n" +
                "PLAY IN TWO WORLDS AT THE SAME TIME\n" +
                "Never-seen-before gameplay that plays out across two worlds displayed at the same time. Explore the physical world and the spirit world simultaneously, and use the interactions between them to solve dual-reality puzzles, unlock new paths, and awaken memories of past events.\n" +
                "\n" +
                "ENTER A DARK REALITY INSPIRED BY BEKSIŃSKI\n" +
                "The Medium’s spirit world is a dark mirror reflection of our reality, a grim and unsettling place where our unpunished deeds, evil urges, and vile secrets manifest themselves and can take on a form. This world has been invented and designed under the inspiration of Zdzisław Beksiński’s paintings, Polish dystopian surrealist internationally recognized for his distinctive and strikingly ominous style.\n" +
                "\n" +
                "MUSIC BY YAMAOKA & REIKOWSKI\n" +
                "Immerse yourself in the disturbing and oppressive atmosphere of the game thanks to the original ‘dual’ soundtrack co-created by Akira Yamaoka and Arkadiusz Reikowski. Yamaoka-san is a legendary Japanese composer best known for his work on the Silent Hill series; Reikowski is a Hollywood Music in Media Awards nominee who worked on such acclaimed horror games as Blair Witch, Layers of Fear, and Observer. Now they join their creative forces for the music and songs of The Medium.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202105/1813/DV0IXPHtc7JRTLoxsKAg0sJh.png?w=440&thumb=false"));
        productRepository.save(new Product("DEATHLOOP", "DEATHLOOP is a next-gen first person shooter from Arkane Lyon, the award-winning studio behind Dishonored. In DEATHLOOP, two rival assassins are trapped in a mysterious timeloop on the island of Blackreef, doomed to repeat the same day for eternity. As Colt, the only chance for escape is to end the cycle by assassinating eight key targets before the day resets. Learn from each cycle - try new paths, gather intel, and find new weapons and abilities. Do whatever it takes to break the loop.\n" +
                "\n" +
                "DESIGNED FOR THE PS5\n" +
                "DEATHLOOP is built for a new generation, leveraging the PlayStation 5's hardware and graphics to bring Arkane’s unique artistic vision to life like never before. DEATHLOOP takes advantage of new cutting-edge features like haptic feedback and adaptive triggers to make each encounter feel unique and impactful.\n" +
                "\n" +
                "IF AT FIRST YOU DON’T SUCCEED... DIE, DIE AGAIN\n" +
                "Every new loop is an opportunity to change things up. Use the knowledge you gain from each attempt to change up your playstyle, stealthily sneaking through levels or barreling into the fight, guns-blazing. In each loop you’ll discover new secrets, gather intel on your targets as well as the island of Blackreef, and expand your arsenal. Armed with a host of otherworldly abilities and savage weaponry, you’ll utilize every tool at your command to execute takedowns that are as striking as they are devastating. Customize your loadout wisely to survive this deadly game of hunter vs hunted.\n" +
                "\n" +
                "SINGLE PLAYER GAMEPLAY INJECTED WITH DEADLY MULTIPLAYER\n" +
                "Are you the hero or the villain? You’ll experience DEATHLOOP’s main story as Colt, hunting down targets across the island of Blackreef to break the loop and earn your freedom. All the while, you’ll be hunted by your rival Julianna, who can be controlled by another player. So if you’re feeling devious, you, too, can step into Julianna’s stylish sneakers and invade another player’s campaign to kill Colt. The multiplayer experience is completely optional, and players can choose to have Julianna controlled by AI within their campaign.\n" +
                "\n" +
                "THE ISLAND OF BLACKREEF – PARADISE OR PRISON\n" +
                "Arkane is renowned for magnificently artistic worlds with multiple pathways and emergent gameplay. DEATHLOOP will present a stunning, retro-future, 60s-inspired environment, that feels like a character within itself. While Blackreef may be a stylish wonderland, for Colt it is his prison, a world ruled by decadence where death has no meaning, and delinquents party forever while keeping him captive.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202007/1617/Fv4asO4zbdqL83hiL9COTlWZ.png?w=440&thumb=false"));
        productRepository.save(new Product("Ghostrunner", "Ghostrunner is a hardcore FPP slasher packed with lightning-fast action, set in a grim, cyberpunk megastructure. Climb Dharma Tower, humanity’s last shelter, after a world-ending cataclysm. Make your way up from the bottom to the top, confront the tyrannical Keymaster, and take your revenge.\n" +
                "\n" +
                "The streets of this tower city are full of violence. Mara the Keymaster rules with an iron fist and little regard for human life.\n" +
                "\n" +
                "As resources diminish, the strong prey on the weak and chaos threatens to consume what little order remains. The decisive last stand is coming. A final attempt to set things right before mankind goes over the edge of extinction.\n" +
                "\n" +
                "As the most advanced blade fighter ever created, you’re always outnumbered but never outclassed. Slice your enemies with a monomolecular katana, dodge bullets with your superhuman reflexes, and employ a variety of specialized techniques to\n" +
                "prevail.\n" +
                "\n" +
                "One-hit one-kill mechanics make combat fast and intense. Use your superior mobility (and frequent checkpoints!) to engage in a never-ending dance with death fearlessly.\n" +
                "\n" +
                "Ghostrunner offers a unique single-player experience: fast-paced, violent combat, and an original setting that blends science fiction with post-apocalyptic themes. It tells the story of a world that has already ended and its inhabitants who fight to survive.\n" +
                "\n" +
                "If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202108/2600/hdoOAnRlmDSm5kCcgggrXnIT.png?w=440&thumb=false"));
        productRepository.save(new Product("Marvel's Guardians of the Galaxy", "This product entitles you to download both the digital PS4™ version and the digital PS5™ version of this game.\n" +
                "\n" +
                "Fire up a wild ride across the cosmos with a fresh take on Marvel’s Guardians of the Galaxy. In this third-person action-adventure game, you are Star-Lord, and thanks to your bold yet questionable leadership, you have persuaded an oddball crew of unlikely heroes to join you. Some jerk (surely not you) has set off a chain of catastrophic events, and only you can hold the unpredictable Guardians together long enough to fight off total interplanetary meltdown. Use Element Blasters, tag-team beat downs, jet boot-powered dropkicks, nothing’s off-limits. If you think it’s all going to plan, you’re in for a world of surprises, with the consequences of your actions guaranteed to keep the Guardians on their toes. In this original Marvel’s Guardians of the Galaxy story, you’ll cross paths with powerful new beings and unique takes on iconic characters, all caught in a struggle for the galaxy’s fate. It’s time to show the universe what you’re made of. You got this. Probably.\n" +
                "\n" +
                "Digital download bonus:\n" +
                "- Social-Lord Outfit for Star-Lord (Early Unlock)\n" +
                "\n" +
                "If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202106/0311/ycYwMaDKZ8nFZd3E8EJz9zfZ.png?w=440&thumb=false"));
        productRepository.save(new Product("Serious Sam 4", "Serious Sam 4 reignites the classic FPS series in a high-powered prequel loaded with an explosive arsenal, intergalactic carnage, and perfectly timed one-liners.\n" +
                "\n" +
                "Humanity is under siege as the full force of Mental’s hordes spread across the world, ravaging what remains of a broken and beaten civilization. The last remaining resistance to the invasion is the Earth Defence Force led by Sam “Serious” Stone and his heavily-armed squad of misfit commandos.\n" +
                "\n" +
                "Croteam returns with a high-powered prequel to the Serious Sam series that scales up chaos to unprecedented levels. The classic Serious Sam formula is revamped by putting an unstoppable arsenal up against an unimaginable number of enemies that requires players to circle-strafe and backpedal-blast their way out of impossible situations.\n" +
                "\n" +
                "HORDES OF INVADERS\n" +
                "The iconic cast of alien invaders returns with some new reinforcements! Fight your way through unbelievable numbers of Mental's minions including the iconic Headless Kamikaze, Beheaded Rocketeer, Kleer, Scrapjack, Werebull, and Khnum! Square off against brand new enemies and towering monsters like the frantic Processed, repulsive Belcher, the hard-hitting Zealot, and more.\n" +
                "\n" +
                "EXPLOSIVE ARSENAL\n" +
                "Armed with a slew of devastating weapons, pick your tool for any situation. Lay waste to Mental's Horde using the powerful double-barreled shotgun, the punishing minigun, the powerful chainsaw launcher, a brand new auto shotgun, and the iconic cannon. Upgrade your toys, and enjoy the violent beauty of the lock-on rocket launcher, and the mighty laser beam of death.\n" +
                "\n" +
                "COOPERATIVE MAYHEM\n" +
                "Smash through the action-packed campaign with friends in 4-player online co-op mode! Tackle exciting primary missions and thrilling side quests in modified difficulties for an extra challenge.\n" +
                "\n" +
                "LEGION SYSTEM\n" +
                "Serious Sam 4 unleashes some of the biggest moments in the series’ history with the new Legion System and battlefields teeming with thousands of enemies!", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202111/2222/E7T6XeKRxvyJK2wWGMLPQU5T.png?w=440&thumb=false"));
        productRepository.save(new Product("UNCHARTED: Legacy of Thieves Collection", "Leave your mark on the map in UNCHARTED: Legacy of Thieves Collection.\n" +
                "\n" +
                "In an experience delivered by award winning developer Naughty Dog, UNCHARTED: Legacy of Thieves Collection includes the two critically acclaimed, single player adventures: UNCHARTED 4: A Thief’s End and UNCHARTED: The Lost Legacy.\n" +
                "\n" +
                "Discover lost history with the charismatic yet complex thieves, Nathan Drake and Chloe Frazer, as they travel the world with a sense of wonder, pursuing extraordinary adventures and lost lore – remastered in stunning detail for the PS5™ console with improved visuals and frame rate.\n" +
                "\n" +
                "Unmissable PlayStation®5 console features:\n" +
                "• DualSense™ wireless controller features: Feel the action unfold with the DualSense™ wireless controller's haptic feedback through punches, vaults, and vehicular traversal. Experience the tension in rope swings via dynamic adaptive triggers.\n" +
                "• Fast loading: Jump back into the adventure quickly with the PS5™ console’s ultra-high speed SSD and near-instant load times.\n" +
                "• 3D Audio: Hear the world of UNCHARTED™: Legacy of Thieves Collection come to life around you with 3D Audio.¹\n" +
                "• Fidelity mode: Play in a super sharp native 4K resolution with a 30fps target frame rate.²\n" +
                "• Performance mode: Play with a smooth 60fps target frame rate.\n" +
                "o 4K TV players will experience a 4K resolution upscaled from a 1440p base resolution.\n" +
                "o 1080p HD TV players will experience a 1080p resolution super sampled from a 1440p base with improved anti-aliasing.\n" +
                "• Performance+ mode: Play with a high impact 120fps target frame rate³ and a 1080p resolution.\n" +
                "\n" +
                "Owners of PS4 disc copies must insert them into the PS5 every time they want to download or play the PS5 digital versions. PS4 game disc owners who buy the PS5 Digital Edition disc-free console will not be able to get the PS5 version for the discounted price.\n" +
                "PlayStation®Plus members who claimed UNCHARTED 4: A Thief’s End via their PlayStation Plus subscription are not eligible for the £10 digital PS5 upgrade.\n" +
                "\n" +
                "¹ 3D Audio via built-in TV speakers (setup and update to the latest system software required) or analogue/USB stereo headphones.\n" +
                "² Requires a 4K compatible TV or display.\n" +
                "³ Requires a compatible 120hz display.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202111/2000/FDEoSfzsxSpuAHyeBTjWBRdC.png?w=440&thumb=false"));
        productRepository.save(new Product("Dying Light 2 Stay Human", "Over twenty years ago in Harran, we fought the virus—and lost. Now, we’re losing again. The City, one of the last large human settlements, is torn by conflict. Civilization has fallen back into the Dark Ages. And yet, we still have hope.\n" +
                "\n" +
                "You are a wanderer with the power to change the fate of The City. But your exceptional abilities come at a price. Haunted by memories you cannot decipher, you set out to learn the truth… and find yourself in a combat zone. Hone your skills, as to defeat your enemies and make allies, you’ll need both fists and wits. Unravel the dark secrets behind the wielders of power, choose sides and decide your destiny. But wherever your actions take you, there's one thing you can never forget—stay human.\n" +
                "\n" +
                "VAST OPEN WORLD\n" +
                "Participate in the life of a city engulfed in a new dark era. Discover different paths and hidden passages, as you explore its multiple levels and locations.\n" +
                "\n" +
                "CREATIVE & BRUTAL COMBAT\n" +
                "Take advantage of your parkour skills to tip the scales of even the most brutal encounter. Clever thinking, traps and creative weapons will be your best friends.\n" +
                "\n" +
                "DAY AND NIGHT CYCLE\n" +
                "Wait for night to venture into dark hideouts of the Infected. Sunlight keeps them at bay, but once it’s gone, monsters begin the hunt, leaving their lairs free to explore.\n" +
                "\n" +
                "CHOICES & CONSEQUENCES\n" +
                "Shape the future of The City with your actions and watch how it changes. Determine the balance of power by making choices in a growing conflict and forge your own experience.\n" +
                "\n" +
                "2-4 PLAYER CO-OP GAMEPLAY\n" +
                "Play in up to four-player co-op. Host your own games or join others and see how their choices have played out differently than yours.", 10.0, "https://image.api.playstation.com/vulcan/img/rnd/202106/2908/7aJhOMuJALdBPqZHVy3CgJsg.png?w=440&thumb=false"));
        productRepository.save(new Product("Horizon Forbidden West", "Join Aloy as she braves the Forbidden West, a deadly frontier that conceals mysterious new threats.\n" +
                "\n" +
                "PlayStation®5 features:\n" +
                "- Fast-travel and get back into the game almost instantly with the PS5 console’s ultra-high-speed SSD and fast load times.\n" +
                "- Play in stunning 4K, HDR and with a Performance Mode targeting 60FPS.*\n" +
                "- Feel impact from attacks with the DualSense™ wireless controller’s haptic feedback and feel the resistance of your bow, grappling tool and other weapons with the DualSense wireless controller’s adaptive triggers.\n" +
                "- Hear sounds from all around you with the PS5 console’s Tempest 3D AudioTech with stereo headphones (analogue or USB).\n" +
                "\n" +
                "*4K and HDR require a 4K and HDR compatible TV or display.\n" +
                "\n" +
                "If you already own the PS4 version of this game, you can get the PS5 digital version at no extra cost and you do not need to purchase this product. Owners of a PS4 disc copy must insert it into the PS5 every time they want to download or play the PS5 digital version. PS4 game disc owners who buy the PS5 Digital Edition disc-free console will not be able to get the PS5 version at no extra cost.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202107/3100/LcBzIX6UFHmJIC04EDFKz6IK.png?w=440&thumb=false"));
        productRepository.save(new Product("Cyberpunk 2077", "Cyberpunk 2077 is an open-world, action-adventure RPG set in the megalopolis of Night City, where you play as a cyberpunk mercenary wrapped up in a do-or-die fight for survival. Upgraded with next-gen in mind and featuring free additional content, customize your character and playstyle as you take on jobs, build a reputation, and unlock upgrades. The relationships you forge and the choices you make will shape the story and the world around you. Legends are made here. What will yours be?\n" +
                "\n" +
                "CREATE YOUR OWN CYBERPUNK\n" +
                "Become an urban outlaw equipped with cybernetic enhancements and build your legend on the streets of Night City.\n" +
                "\n" +
                "EXPLORE THE CITY OF THE FUTURE\n" +
                "Night City is packed to the brim with things to do, places to see, and people to meet. And it’s up to you where to go, when to go, and how to get there.\n" +
                "\n" +
                "BUILD YOUR LEGEND\n" +
                "Go on daring adventures and build relationships with unforgettable characters whose fates are shaped by the choices you make.\n" +
                "\n" +
                "EQUIPPED WITH NEXT-GEN FEATURES\n" +
                "Immerse yourself in Night City like never before thanks to faster load times, improved visual and gameplay features, ray tracing and dynamic 4K support, and more. All part of a free upgrade for current owners of Cyberpunk 2077.\n" +
                "\n" +
                "INCLUDES FREE ADDITIONAL CONTENT\n" +
                "Get your hands on a haul of free items including new guns and melee weapons, as well as extra customization options and more.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202111/3013/cKZ4tKNFj9C00giTzYtH8PF1.png?w=440&thumb=false"));
        productRepository.save(new Product("Demon's Souls", "From PlayStation Studios and Bluepoint Games comes a remake of the PlayStation classic, Demon's Souls. Entirely rebuilt from the ground up and masterfully enhanced, this remake introduces the horrors of a fog-laden, dark fantasy land to a whole new generation of gamers. Those who've faced its trials and tribulations before can once again challenge the darkness in stunning visual quality and incredible performance.\n" +
                "\n" +
                "In his quest for power, the 12th King of Boletaria, King Allant, channelled the ancient Soul Arts, awakening a demon from the dawn of time itself, The Old One.\n" +
                "With the summoning of The Old One, a colourless fog swept across the land, unleashing nightmarish creatures that hungered for human souls. Those whose souls were stripped from them lost their minds — left only with the desire to attack the sane that remained.\n" +
                "\n" +
                "Now, Boletaria is cut off from the outside world, and the knights who dare penetrate the deep fog to free the land from its plight are never seen again. As a lone warrior who has braved the baneful fog, you must face the hardest of challenges to earn the title \"Slayer of Demons\" and send The Old One back to its slumber.", 10.0, "https://image.api.playstation.com/vulcan/img/rnd/202011/1717/GemRaOZaCMhGxQ9dRhnQQyT5.png?w=440&thumb=false"));
        productRepository.save(new Product("Ghostwire: Tokyo", "Tokyo is overrun by deadly supernatural forces, perpetrated by a dangerous occultist, causing Tokyo’s population to vanish in an instant. Ally with a powerful spectral entity on their quest for vengeance and master a powerful arsenal of abilities to unravel the dark truth behind the disappearance as you FACE THE UNKNOWN in Ghostwire: Tokyo.\n" +
                "\n" +
                "With the power and speed of the PlayStation®5 console, Tango Gameworks forges a beautiful, supernatural version of modern Tokyo. Using unparalleled haptic feedback for every ability and character action with the DualSense™ wireless controller and advanced 3D spatial audio, immerse yourself in an ominous city filled with dangers and uncover new mysteries around every corner.\n" +
                "\n" +
                "A beautifully haunted Tokyo\n" +
                "Explore a unique vision of Tokyo twisted by a supernatural presence. From its ultra-modern cityscape to its traditional temples and narrow alleyways, discover a hauntingly beautiful city teeming with Yokai - vengeful spirits that prowl the streets. Discover iconic landmarks like Shibuya Crossing and Tokyo Tower, frozen in time when the city’s population disappeared and travel to the surreal underworld on your quest to save your family.\n" +
                "\n" +
                "Next-Gen Immersion\n" +
                "Experience a stunningly rendered, rain-soaked Tokyo with PlayStation 5's next-gen ray tracing technology and 3D AudioTech. Feel the power of your supernatural skills firsthand with unique haptic feedback and adaptive triggers for every combat ability and character action in-game.\n" +
                "\n" +
                "Devastating Elemental Abilities\n" +
                "Wield a combination of upgradeable elemental powers and ghost-hunting skills to combat the supernatural threat. Use your ethereal abilities to ascend to the to the top of Tokyo's skyline and soar over the streets to discover new missions or even get the drop on your enemies.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202202/1522/n6qx9GWCjkivDuCDsHhBwzhF.png?w=440&thumb=false"));
        productRepository.save(new Product("Ratchet & Clank: Rift Apart", "BLAST YOUR WAY THROUGH AN INTERDIMENSIONAL ADVENTURE\n" +
                "\n" +
                "Ratchet and Clank are back! Help them stop a robotic emperor intent on conquering cross-dimensional worlds, with their own universe next in the firing line. Witness the evolution of the dream team as they’re joined by Rivet – a Lombax resistance fighter from another dimension.\n" +
                "\n" +
                "- Blast your way home with an arsenal of outrageous weaponry.\n" +
                "- Experience the shuffle of dimensional rifts and dynamic gameplay.\n" +
                "- Explore never-before-seen planets and parallel universe versions of old favourites.\n" +
                "\n" +
                "PS5™ FEATURES:\n" +
                "- Feel in-game actions through the haptic feedback of the DualSense™ wireless controller.\n" +
                "- Take full control of advanced weapon mechanics, made possible by adaptive triggers.\n" +
                "- Planet-hop at hyper-speed via the near-instant loading of the PS5™ console’s SSD.\n" +
                "- Immerse your ears with Tempest 3D AudioTech* as you work to save the universe.\n" +
                "- Enhanced lighting and ray tracing render dazzling in-game worlds – displayed in crisp, dynamic 4K and HDR**.\n" +
                "- Choose Performance Mode to enjoy targeted 60 frames per second gameplay***.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202101/2921/DwVjpbKOsFOyPdNzmSTSWuxG.png?w=440&thumb=false"));
        productRepository.save(new Product("Returnal", "BREAK THE CYCLE\n" +
                "\n" +
                "After crash-landing on a shape-shifting alien planet, Selene finds herself fighting tooth and nail for survival. Again and again she’s defeated, forced to restart her journey every time she dies.\n" +
                "\n" +
                "In this roguelike shooter, both the planet and your equipment change with every cycle, forcing you to adapt your play style and take on evolving challenges.\n" +
                "\n" +
                "Engage enemies in bullet hell-fuelled clashes.\n" +
                "Scavenge alien tech for upgrades to your abilities.\n" +
                "Forge a personal connection with the planet and piece together Selene’s story.\n" +
                "\n" +
                "PLAYSTATION®5 IN-GAME FEATURES:\n" +
                "Fast loading: near-instant loading speeds of the PS5™ console get you rapidly back into the action.\n" +
                "Tempest 3D AudioTech: Hear multidirectional 3D Audio with compatible headphones — bullets flying past you, enemies circling overhead, or unseen foes creeping up behind you.\n" +
                "Adaptive triggers: switch between firing modes with a single adaptive trigger – go from aiming down sights to your gun’s alternative fire.\n" +
                "Haptic feedback: sense in-game actions – while exploring fragments of Selene’s memories, or when firing visceral alien weapons.", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202011/1621/fYZQHZ42eXXUt7c6D5YjLrq5.png?w=440&thumb=false"));
        productRepository.save(new Product("Elden Ring", "THE NEW FANTASY ACTION RPG.\n" +
                "Rise, Tarnished, and be guided by grace to brandish the power of the Elden Ring and become an Elden Lord in the Lands Between.\n" +
                "\n" +
                "• A Vast World Full of Excitement\n" +
                "A vast world where open fields with a variety of situations and huge dungeons with complex and three-dimensional designs are seamlessly connected. As you explore, the joy of discovering unknown and overwhelming threats await you, leading to a high sense of accomplishment.\n" +
                "\n" +
                "• Create your Own Character\n" +
                "In addition to customizing the appearance of your character, you can freely combine the weapons, armor, and magic that you equip. You can develop your character according to your play style, such as increasing your muscle strength to become a strong warrior, or mastering magic.\n" +
                "\n" +
                "• An Epic Drama Born from a Myth\n" +
                "A multilayered story told in fragments. An epic drama in which the various thoughts of the characters intersect in the Lands Between.\n" +
                "\n" +
                "• Unique Online Play that Loosely Connects You to Others\n" +
                "In addition to multiplayer, where you can directly connect with other players and travel together, the game supports a unique asynchronous online element that allows you to feel the presence of others.\n" +
                "\n" +
                "[If you already own the PS4™ version of this game, you can get the PS5™ digital version at no extra cost and you do not need to purchase this product. Owners of a PS4™ disc copy must insert it into the PS5™ every time they want to download or play the PS5™ digital version. PS4™ game disc owners who buy the PS5™ Digital Edition disc-free console will not be able to get the PS5™ version at no extra cost.]", 10.0, "https://image.api.playstation.com/vulcan/ap/rnd/202108/0410/0Jz6uJLxOK7JOMMfcfHFBi1D.png?w=440&thumb=false"));
        productRepository.save(new Product("God of War", "Living as a man outside the shadow of the gods, Kratos must adapt to unfamiliar lands, unexpected threats and a second chance at being a father. Together with his son Atreus, the pair will venture into the brutal realm of Midgard and fight to fulfil a deeply personal quest.\n" +
                "• Journey to a dark, elemental world of fearsome creatures – straight from the pages of Norse mythology.\n" +
                "• Conquer your foes in vicious close-quarters combat that rewards tactical thinking and lethal precision.\n" +
                "• Discover an emotionally gripping tale of father and son, as Kratos is made to control the rage that has long defined him.", 10.0, "https://image.api.playstation.com/vulcan/img/rnd/202010/2217/RnDzysOs81fdvhdQAHYtxjFT.png?w=440&thumb=false"));

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }
}
