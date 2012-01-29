jQuery( document ).ready( function ( $ ) {

  if( $( "#RegisterForm" ).size() > 0 ) {

    var geoLocation = navigator.geolocation;
    if( geoLocation ) {
      geoLocation.getCurrentPosition( function ( data ) {
        $( "#latitude" ).val( data.coords.latitude );
        $( "#longitude" ).val( data.coords.longitude );
      } );
    }

  }

} );

jQuery( document ).ready( function ( $ ) {

  if( $( "#geolocation" ).size() > 0 ) {
      navigator.geolocation.getCurrentPosition( function ( data ) {
        $( "#latitude" ).val( data.coords.latitude );
        $( "#longitude" ).val( data.coords.longitude );
        
      } );
  }

} );