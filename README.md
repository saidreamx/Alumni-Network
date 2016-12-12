# Alumni-Network

An Android application which connects Alumni to College Students. 
Though with some minor changes, one always use it for any application that requires a social network kind of thing.

This application uses the following libraries:
1. Retrofit 1.9
2. Picasso
3. Circular image view by mikhaellopez

All the networking part has been done through Retrofit.
Picasso has been used to get image from a given URL.
Circular image view has been used to display profile images in the activity.
I have hosted the application on hostinger.com, a free service.

How to use Retrofit for networking event:
1. Make a POJO model for your JSON type. To do so one can always use jsonschema2pojo.org.
   In my application I have created a wrapper for each POJO just because I get a different format of JSON from server.
   It is not necessary to make such classes unless one does the same. SO be certain about the format of JSON.
2. Make an API defining your callback and functions.
3. Create Rest adapter and code according to requirement.
For detailed information visit futurestudio documentation for Retrofit.

How to use Picasso:
We just need a single line of code to actually fetch our image from server.
