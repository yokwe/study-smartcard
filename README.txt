Summary
-------
This is Java application that read data from smartcard.
I create this project because I want to see what data is stored in my credit card.


Story of this project
---------------------
My curiosity starts the project. After writing few hundreds line of code,
I realized complexity of data structure of smart card. I mean nested BER data
and interpretation of it. Because more than one standards (EMV, GP, ISO, ...) defined
structure of data and they coexist in BER data.

After writing few thousands of lines, I understand that I should separate structure of data
and interpretation of data. Because I need to edit code to add new data structure for
standard documents. And defining data structure in code reduced re-usability of code.

To solve this problem, I defined data structure in text file called Element.data.
The file define tag name, tag number, template (parent) of the tag in one line.
Usually standards has tables of these information to define data structure.
So I can use information defined in standards to define structure.

The annotation is used to assign symbolic name to class interpret value of data.
The reflection is used to locate the annotated class.


License
-------
This software is released under the BSD License, see LICENSE.txt.


Disclaimer
----------
All trademarks and registered trademarks mentioned herein are
the property of their respective owners.


// Yasuhiro Hasegawa AKA Hasegawa:FXIS:Fuji Xerox