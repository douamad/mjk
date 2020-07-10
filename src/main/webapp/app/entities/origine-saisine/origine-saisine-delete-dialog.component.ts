import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrigineSaisine } from 'app/shared/model/origine-saisine.model';
import { OrigineSaisineService } from './origine-saisine.service';

@Component({
  templateUrl: './origine-saisine-delete-dialog.component.html',
})
export class OrigineSaisineDeleteDialogComponent {
  origineSaisine?: IOrigineSaisine;

  constructor(
    protected origineSaisineService: OrigineSaisineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.origineSaisineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('origineSaisineListModification');
      this.activeModal.close();
    });
  }
}
