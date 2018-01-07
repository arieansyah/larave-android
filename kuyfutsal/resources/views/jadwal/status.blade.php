<div class="modal" id="modal-form" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
   <div class="modal-dialog modal-lg">
      <div class="modal-content">

        <form class="form-horizontal" data-toggle="validator" method="post" >
        {{ csrf_field() }} {{ method_field('POST') }}

   <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"> &times; </span> </button>
      <h3 class="modal-title"></h3>
   </div>

<div class="modal-body">

   <input type="hidden" id="id" name="jadwal_id">
   <input type="hidden" name="status" id="status">
   <div class="form-group">
      <label for="nama" class="col-md-3 control-label">Nama</label>
      <div class="col-md-6">
         <input id="nama" type="text" class="form-control" name="nama" autofocus required>
         <span class="help-block with-errors"></span>
      </div>
   </div>
   <div class="form-group">
      <label for="no_handphone" class="col-md-3 control-label">Nomor Handphone</label>
      <div class="col-md-6">
         <input id="no_hanphone" type="text" class="form-control" name="no_hanphone" autofocus required>
         <span class="help-block with-errors"></span>
      </div>
   </div>

</div>
   <div class="modal-footer">
      <button type="submit" class="btn btn-primary btn-save"><i class="fa fa-floppy-o"></i> Simpan </button>
   </div>

</form>

         </div>
      </div>
    </div>
