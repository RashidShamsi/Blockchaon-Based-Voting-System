//
// Envoie les données du formulaire par Ajax 
// Ajoute des classes error et succès suivant la réponse d'Ajax 
// Réiniatlisation du formulaire lors d'un full succès

$('form#FormMessage').on('submit', function(e){
    e.preventDefault();
    console.log("Formulaire envoyer");
    // Récupération des données du formulaire pour un envoi par _POST
    var data_ajax = $(this).serialize()+"&z=message&o=send";
    $.ajax({
          url: 'api/privee/data', // URL ou envoyer les données
          data: data_ajax, // Données à envoyer (formulaire)
          type: "POST", // Méthode à utiliser
          dataType: "json", // Forma de données retournées
          success: function(json) {   

                                  console.log(json); // Logs des données reçus 
                                  
                                  if(parseInt(json)) // OK si c'est un nombre (je renvoie l'id de l'insertion SQL)
                                    {
                                      console.log("Message Envoyer");
                                      $('#ModalMessage').modal('hide'); // Masquer le modal
                                      // Reset des Class CSS d'erreur
                                      $('form#FormMessage input[name="sujet"]').parent().removeClass('has-error has-success');
                                      $('#FormMessageMessage').parent().removeClass('has-error has-success');
                                      $('#FormMessageDestinatiare').parent().removeClass('has-error has-success');
                                      // Reset du formulaire
                                      document.getElementById("FormMessage").reset();
                                    }
                                  else if(json.error) // Une Erreur ?
                                    {
                                      // S'il y a une erreur on ajoute les classe .has-error ou sinon .success
                                      if(json.destinataire){$('#FormMessageDestinatiare').parent().removeClass('has-error has-success').addClass('has-success');}
                                        else{console.log('Erreur de destinataire');$('#FormMessageDestinatiare').parent().addClass('has-error');}
                                      if(json.sujet){$('form#FormMessage input[name="sujet"]').parent().removeClass('has-error has-success').addClass('has-success');}
                                        else{console.log('Erreur de sujet');$('form#FormMessage input[name="sujet"]').parent().addClass('has-error');}
                                      if(json.message){$('#FormMessageMessage').parent().removeClass('has-error has-success').addClass('has-success');}
                                        else{console.log('Erreur de message');$('#FormMessageMessage').parent().addClass('has-error');}
                                    }
                      }
        });
    
  });