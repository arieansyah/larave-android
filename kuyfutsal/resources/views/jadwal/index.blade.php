@extends('base')

@section('title')
  Data Jadwal
@endsection

@section('breadcrumb')
   @parent
   <li>Jadwal</li>
@endsection

@section('content')
<div class="row">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        @include('jadwal.tanggal')
        {{-- <a onclick="periodeForm()" class="btn btn-success"><i class="fa fa-plus-circle"></i> Tanggal</a> --}}
        {{-- <a onclick="addForm()" class="btn btn-success"><i class="fa fa-plus-circle"></i> Tambah</a> --}}
      </div>
      <div class="box-body">
        <table class="table table-striped">
        <thead>
           <tr>
              <th width="30">No</th>
              <th>Jam</th>
              <th>Status</th>
              <th width="25%">Aksi</th>
           </tr>
        </thead>
        <tbody></tbody>
        </table>
      </div>
    </div>
  </div>
</div>

@include('jadwal.status')

@endsection

@section('script')
<script type="text/javascript">
var table, save_method;
$(function(){

  var tanggal = $('select[name=tanggal]').val();
  var bulan = $('select[name=bulan]').val();
  var tahun = $('select[name=tahun]').val();
  $('select[name=tanggal]').on('change', function(){
      tanggal = $('select[name=tanggal]').val();
      console.log(tanggal);
  });
  $('select[name=bulan]').on('change', function(){
      bulan = $('select[name=bulan]').val();
      console.log(bulan);
  });
  $('select[name=tahun]').on('change', function(){
      tahun = $('select[name=tahun]').val();
      console.log(tahun);
  });
  var cari = $('.cari');
  cari.click(function(e){
      e.preventDefault();
      window.location.href = "{{ URL::to('jadwal')}}" + "/" +tahun+ "/" +bulan+ "/" +tanggal;
  });


   table = $('.table').DataTable({
     "processing" : true,
     "paging": false,
     "searching": false,
     "serverSide": true,
     "ajax" : {
       "url" : "jadwal/data/",
       "type" : "GET"
     }
   });

   $('#modal-form form').validator().on('submit', function(e){
      if(!e.isDefaultPrevented()){
         var id = $('#id').val();
         if(save_method == "add")
          url = "{{ route('jadwal.store') }}";
         else
          url = "jadwal/"+id;


         $.ajax({
           url : url,
           type : "POST",
           data : $('#modal-form form').serialize(),
           success : function(data){
             $('#modal-form').modal('hide');
             table.ajax.reload();
           },
           error : function(){
             alert("Tidak dapat menyimpan data!");
           }
         });
        return false;
      }
   });
});


function periodeForm(){
   $('#modal-tanggal').modal('show');
}

function booking(id, status){
   save_method = "add";
   $('input[name=_method]').val('POST');

   $('#modal-form form')[0].reset();

   $.ajax({
     url : "jadwal/"+id+"/edit",
     type : "GET",
     dataType : "JSON",
     success : function(data){
       $('#modal-form').modal('show');
       $('.modal-title').text('Konfirmasi');
       $('#id').val(data.id_jadwal);
       $('#status').val($('#booking').text());
     },
     error : function(){
       alert("Tidak dapat menampilkan data!");
     }
   });
}

function proses(id, status){
   save_method = "add";
   $('input[name=_method]').val('POST');

   $('#modal-form form')[0].reset();

   $.ajax({
     url : "jadwal/"+id+"/edit",
     type : "GET",
     dataType : "JSON",
     success : function(data){
       $('#modal-form').modal('show');
       $('.modal-title').text('Konfirmasi');
       $('#id').val(data.id_jadwal);
       $('#status').val($('#proses').text());
     },
     error : function(){
       alert("Tidak dapat menampilkan data!");
     }
   });
}

function editForm(id, status){
   save_method = "edit";
   $('input[name=_method]').val('PATCH');
   $('#modal-form form')[0].reset();
   $.ajax({
     url : "jadwal/"+id+"/editAfter",
     type : "GET",
     dataType : "JSON",
     success : function(data){
       $('#modal-form').modal('show');
       $('.modal-title').text('Konfirmasi');
       $('#id').val(data.jadwal_id);
       $('#nama').val(data.nama);
       $('#no_hanphone').val(data.no_hanphone);
       $('#status').val($('#booking').text());
     },
     error : function(){
       alert("Tidak dapat menampilkan data!");
     }
   });
}

function deleteData(id){
   if(confirm("Apakah yakin data akan cancel?")){
     $.ajax({
       url : "jadwal/"+id,
       type : "POST",
       data : {'_method' : 'DELETE', '_token' : $('meta[name=csrf-token]').attr('content')},
       success : function(data){
         table.ajax.reload();
       },
       error : function(){
         alert("Tidak dapat menghapus data!");
       }
     });
   }
}
</script>
@endsection
