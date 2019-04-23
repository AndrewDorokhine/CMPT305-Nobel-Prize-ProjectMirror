# CMPT305-Nobel-Prize-Project
Group 8

By: Nemi Rai, Andrew Dorokhine, Seth Thompson, and Sitharthan Elango.

Description.......An information exploration project, gathering data from the Nobel Prize API 
		  (https://nobelprize.readme.io/) and images from the MediaWiki API
	          (https://www.mediawiki.org/wiki/MediaWiki). The information is displayed
		  in a window, and has 2 search functions (Advanced and basic). 
		  
![ListSearch](https://user-images.githubusercontent.com/48187729/56619130-cb0a2f00-65e1-11e9-817c-7d898f3ac276.PNG)

![SearchResult](https://user-images.githubusercontent.com/48187729/56619132-cd6c8900-65e1-11e9-9dd5-722eb84465bc.PNG)

How It works......Data is collected from the Nobel Prize API, parsed with GSON, and then
                  further processed into Maps for faster searching while the program is 
		  running. The root node of the window is a BorderPane and the top, left and
		  center portions are utilized. The BorderPane is created, along with the
		  top, left, and center nodes. The center node is initially populated with
		  all of the laureates.
		
		  Images are stored locally for faster loading times, There is a API.Picture
		  package that contains the functions which we used to get the images from
		  the MediaWiki API.

---------------------------------------------------------------------------------------------------

Packages:

nobelprizemain....Contains the main function, and the driver for the program.
Window............Contains all of the JavaFX nodes to be shown on the stage.
API...............Contains the data retrieved form the NobelPrizeAPI, and searching
		  functions.

***There is an UML located in the "/NobelPrize/UML" both in Yed and PDF format***

---------------------------------------------------------------------------------------------------

Known issues:

- Every advanced search has an entry in the list where each field is set to "null"
- Not all images are accurate (<5 of them), such as "Ernst Rudska" produces an image of
  an electron microscope instead of the actual person.
